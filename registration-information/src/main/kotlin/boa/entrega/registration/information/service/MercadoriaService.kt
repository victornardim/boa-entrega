package boa.entrega.registration.information.service

import boa.entrega.registration.information.model.domain.Mercadoria
import boa.entrega.registration.information.model.dto.MercadoriaSlimDto
import boa.entrega.registration.information.publisher.MercadoriaPublisher
import boa.entrega.registration.information.repository.MercadoriaRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.*

@Service
class MercadoriaService(
    private val repository: MercadoriaRepository,
    private val fornecedorService: FornecedorService,
    private val depositoService: DepositoService,
    private val publisher: MercadoriaPublisher
) {
    private val logger = LoggerFactory.getLogger(MercadoriaService::class.java)

    fun listByFornecedor(fornecedorId: UUID, offset: Int, limit: Int): Collection<Mercadoria> =
        repository.listByFornecedor(fornecedorId, offset, limit)
            .map { it.toComplete(fornecedorService.get(it.fornecedorId), depositoService.get(it.depositoId)) }

    fun listByDeposito(depositoId:UUID, offset: Int, limit: Int): Collection<Mercadoria> =
        repository.listByDeposito(depositoId, offset, limit)
            .map { it.toComplete(fornecedorService.get(it.fornecedorId), depositoService.get(it.depositoId)) }

    fun get(id: UUID): Mercadoria =
        repository.get(id).let {
            it.toComplete(fornecedorService.get(it.fornecedorId), depositoService.get(it.depositoId))
        }

    fun create(mercadoriaSlimDto: MercadoriaSlimDto) {
        val id = UUID.randomUUID()
        repository.create(mercadoriaSlimDto.toDomain(id))
        publisher.publishCreate(repository.get(id).toEvent())
        logger.info("Created new merchandise ${mercadoriaSlimDto.toDomain(id)}")
    }

    fun update(id: UUID, mercadoriaSlimDto: MercadoriaSlimDto) {
        repository.update(mercadoriaSlimDto.toDomain(id))
        publisher.publishUpdate(repository.get(id).toEvent())
        logger.info("Updated merchandise ${mercadoriaSlimDto.toDomain(id)}")
    }

    fun updateQuantity(id: UUID, quantidade: Double) {
        repository.updateQuantity(id, quantidade)
        publisher.publishUpdate(repository.get(id).toEvent())
        logger.info("Updated merchandise $id with quantity $quantidade")
    }

    fun delete(id: UUID) {
        repository.delete(id)
        publisher.publishDelete(repository.get(id).toEvent())
        logger.info("Deleted merchandise $id")
    }
}