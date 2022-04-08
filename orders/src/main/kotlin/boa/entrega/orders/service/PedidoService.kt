package boa.entrega.orders.service

import boa.entrega.orders.exception.dto.BadRequestException
import boa.entrega.orders.exception.dto.EntityNotFoundException
import boa.entrega.orders.model.domain.Andamento
import boa.entrega.orders.model.domain.AndamentoStatus
import boa.entrega.orders.model.domain.Pedido
import boa.entrega.orders.model.form.MercadoriaUpdateQuantidadeBatchForm
import boa.entrega.orders.model.form.PedidoCreateForm
import boa.entrega.orders.model.slim.PedidoSlim
import boa.entrega.orders.model.slim.RequisicaoSlim
import boa.entrega.orders.publisher.PedidoPublisher
import boa.entrega.orders.repository.PedidoRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.Instant
import java.util.UUID

@Service
class PedidoService(
    private val repository: PedidoRepository,
    private val publisher: PedidoPublisher,
    private val mercadoriaPedidoService: MercadoriaPedidoService,
    private val andamentoPedidoService: AndamentoPedidoService,
    private val mercadoriaService: MercadoriaService,
    private val clienteService: ClienteService,
    private val requisicaoPedidoService: RequisicaoPedidoService
) {
    private val logger = LoggerFactory.getLogger(PedidoService::class.java)

    fun listByAndamento(andamento: AndamentoStatus, offset: Int, limit: Int): Collection<Pedido> =
        repository.listByAndamento(andamento, offset, limit).map {
            getCompleteOrder(it)
        }

    fun listByCliente(clienteId: UUID, offset: Int, limit: Int): Collection<Pedido> =
        repository.listByCliente(clienteId, offset, limit).map {
            getCompleteOrder(it)
        }

    fun get(id: UUID): Pedido =
        getCompleteOrder(repository.get(id))

    fun getByCodigoRastreio(codigoRastreio: String): Pedido =
        getCompleteOrder(repository.getByCodigoRastreio(codigoRastreio))

    @Transactional
    fun create(pedidoCreateForm: PedidoCreateForm): UUID {
        val id = UUID.randomUUID()
        repository.create(pedidoCreateForm.toSlim(id))
        andamentoPedidoService.create(
            id,
            Andamento(
                id = UUID.randomUUID(),
                data = Instant.now(),
                status = AndamentoStatus.CRIACAO
            )
        )

        val mercadoriaQuantidadeUpdateBatchForms: Collection<MercadoriaUpdateQuantidadeBatchForm> = pedidoCreateForm.mercadorias
            .map { mercadoriaCreateForm ->
                mercadoriaPedidoService.create(id, mercadoriaCreateForm)

                MercadoriaUpdateQuantidadeBatchForm(
                    mercadoriaCreateForm.id,
                    mercadoriaCreateForm.quantidade
                )
            }

        val requisicaoId = mercadoriaService.updateQuantidade(mercadoriaQuantidadeUpdateBatchForms)
        requisicaoPedidoService.create(
            RequisicaoSlim(
                requisicaoId = requisicaoId,
                pedidoId = id
            )
        )
        logger.info("Created new order ${pedidoCreateForm.toSlim(id)}")

        return id
    }

    fun finishCreate(id: UUID) {
        proceed(id)

        publisher.publishCreate(
            repository.get(id).toMessage(
                ultimoAndamento = andamentoPedidoService.getLastByPedido(id),
                mercadorias = mercadoriaPedidoService.listByPedido(id)
            )
        )
    }

    fun abort(id: UUID) {
        andamentoPedidoService.create(
            id,
            Andamento(
                id = UUID.randomUUID(),
                data = Instant.now(),
                status = getAbortedStatus(id, andamentoPedidoService.getLastByPedido(id).status)
            )
        )

        logger.warn("The order $id has been aborted")
    }

    fun proceed(id: UUID) {
        andamentoPedidoService.create(
            id,
            Andamento(
                id = UUID.randomUUID(),
                data = Instant.now(),
                status = getNextStatus(id, andamentoPedidoService.getLastByPedido(id).status)
            )
        )

        val lastStep = andamentoPedidoService.getLastByPedido(id)
        publisher.publishNextStep(
            repository.get(id).toMessage(
                ultimoAndamento = lastStep,
                mercadorias = mercadoriaPedidoService.listByPedido(id)
            )
        )
        logger.info("Order $id advanced to the next step ${lastStep.status.name}")
    }

    fun cancel(id: UUID) {
        andamentoPedidoService.create(
            id,
            Andamento(
                id = UUID.randomUUID(),
                data = Instant.now(),
                status = getCanceledStatus(id, andamentoPedidoService.getLastByPedido(id).status)
            )
        )

        publisher.publishCancel(
            repository.get(id).toMessage(
                ultimoAndamento = andamentoPedidoService.getLastByPedido(id),
                mercadorias = mercadoriaPedidoService.listByPedido(id)
            )
        )
        logger.info("Order $id has been canceled")
    }

    private fun getCompleteOrder(pedidoSlim: PedidoSlim): Pedido {
        return pedidoSlim.toComplete(
            cliente = pedidoSlim.clienteId.let { clienteId ->
                clienteService.get(clienteId)?.toDomain() ?: let {
                    logger.warn("Client $clienteId not found")
                    throw EntityNotFoundException("Client $clienteId not found")
                }
            },
            andamento = andamentoPedidoService.listByPedido(pedidoSlim.id),
            mercadorias = mercadoriaPedidoService.listByPedido(pedidoSlim.id).map { mercadoriaPedido ->
                mercadoriaService.get(mercadoriaPedido.mercadoriaId)?.copy(
                    quantidade = mercadoriaPedido.quantidade
                )?.toDomain() ?: let {
                    logger.warn("Merchandise ${mercadoriaPedido.mercadoriaId} not found")
                    throw EntityNotFoundException("Merchandise ${mercadoriaPedido.mercadoriaId} not found")
                }
            }
        )
    }

    private fun getNextStatus(pedidoId: UUID, current: AndamentoStatus): AndamentoStatus =
        when (current) {
            AndamentoStatus.CRIACAO -> AndamentoStatus.SEPARACAO
            AndamentoStatus.SEPARACAO -> AndamentoStatus.TRANSPORTE
            AndamentoStatus.TRANSPORTE -> AndamentoStatus.ENTREGUE
            AndamentoStatus.ENTREGUE -> {
                logger.error("There is no further status for the order $pedidoId")
                throw BadRequestException("There is no further status for the order $pedidoId")
            }
            AndamentoStatus.CANCELADO -> {
                logger.error("The order $pedidoId is already canceled, you can't change its status")
                throw BadRequestException("The order $pedidoId is already canceled, you can't change its status")
            }
            AndamentoStatus.ABORTADO -> {
                logger.error("The order $pedidoId was aborted, you can't change its status")
                throw BadRequestException("The order $pedidoId was aborted, you can't change its status")
            }
        }

    private fun getCanceledStatus(pedidoId: UUID, current: AndamentoStatus): AndamentoStatus =
        when (current) {
            AndamentoStatus.ENTREGUE -> {
                logger.error("The order $pedidoId is already delivered, you can't change its status")
                throw BadRequestException("The order $pedidoId is already delivered, you can't change its status")
            }
            AndamentoStatus.CANCELADO -> {
                logger.error("The order $pedidoId is already canceled")
                throw BadRequestException("The order $pedidoId is already canceled")
            }
            AndamentoStatus.ABORTADO -> {
                logger.error("The order $pedidoId was aborted, you can't change its status")
                throw BadRequestException("The order $pedidoId was aborted, you can't change its status")
            }
            else -> AndamentoStatus.CANCELADO
        }

    private fun getAbortedStatus(pedidoId: UUID, current: AndamentoStatus): AndamentoStatus =
        when (current) {
            AndamentoStatus.CRIACAO -> AndamentoStatus.ABORTADO
            else -> {
                logger.error("The order $pedidoId can't be aborted, because it's already started")
                throw BadRequestException("The order $pedidoId can't be aborted, because it's already started")
            }
        }
}
