package boa.entrega.registration.information.service

import boa.entrega.registration.information.exception.dto.BadRequestException
import boa.entrega.registration.information.model.domain.Mercadoria
import boa.entrega.registration.information.model.form.MercadoriaCreateForm
import boa.entrega.registration.information.publisher.MercadoriaPublisher
import boa.entrega.registration.information.repository.MercadoriaRepository
import org.slf4j.LoggerFactory
import org.springframework.cache.annotation.CacheEvict
import org.springframework.stereotype.Service
import java.util.UUID

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

    fun listByDeposito(depositoId: UUID, offset: Int, limit: Int): Collection<Mercadoria> =
        repository.listByDeposito(depositoId, offset, limit)
            .map { it.toComplete(fornecedorService.get(it.fornecedorId), depositoService.get(it.depositoId)) }

    fun get(id: UUID): Mercadoria =
        repository.get(id).let {
            it.toComplete(fornecedorService.get(it.fornecedorId), depositoService.get(it.depositoId))
        }

    fun create(mercadoriaCreateForm: MercadoriaCreateForm) {
        val id = UUID.randomUUID()
        repository.create(mercadoriaCreateForm.toSlim(id))
        publisher.publishCreate(repository.get(id).toMessage())
        logger.info("Created new merchandise ${mercadoriaCreateForm.toSlim(id)}")
    }

    fun update(id: UUID, mercadoriaCreateForm: MercadoriaCreateForm) {
        repository.update(mercadoriaCreateForm.toSlim(id))
        publisher.publishUpdate(repository.get(id).toMessage())
        logger.info("Updated merchandise ${mercadoriaCreateForm.toSlim(id)}")
    }

    fun increaseQuantidade(id: UUID, quantidade: Double) {
        val currentQuantidade = repository.getQuantidade(id)
        val updatedQuantidade = currentQuantidade + quantidade
        repository.updateQuantity(id, updatedQuantidade)
        publisher.publishUpdate(repository.get(id).toMessage())
        logger.info("Increased merchandise $id quantity by $quantidade resulting in $updatedQuantidade")
    }

    @CacheEvict(value = ["mercadoria"], key = "{#id}")
    fun decreaseQuantidade(id: UUID, quantidade: Double) {
        val currentQuantidade = repository.getQuantidade(id)
        (currentQuantidade - quantidade).takeIf { it >= 0 }?.let { updatedQuantidade ->
            repository.updateQuantity(id, updatedQuantidade)
            publisher.publishUpdate(repository.get(id).toMessage())
            logger.info("Decreased merchandise $id quantity by $quantidade resulting in $updatedQuantidade")
        } ?: let {
            logger.error("Tried to decreased merchandise $id quantity by $quantidade, but it just have $currentQuantidade")
            throw BadRequestException("Tried to decreased merchandise $id quantity by $quantidade, but it just have $currentQuantidade")
        }
    }

    fun delete(id: UUID) {
        repository.delete(id)
        publisher.publishDelete(repository.get(id).toMessage())
        logger.info("Deleted merchandise $id")
    }
}
