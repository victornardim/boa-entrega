package boa.entrega.registration.information.facade

import boa.entrega.registration.information.model.domain.Mercadoria
import boa.entrega.registration.information.model.form.MercadoriaCreateForm
import boa.entrega.registration.information.service.MercadoriaService
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class MercadoriaFacade(
    private val service: MercadoriaService
) {
    fun listByFornecedor(fornecedorId: UUID, offset: Int, limit: Int): Collection<Mercadoria> =
        service.listByFornecedor(fornecedorId, offset, limit)

    fun listByDeposito(depositoId: UUID, offset: Int, limit: Int): Collection<Mercadoria> =
        service.listByDeposito(depositoId, offset, limit)

    fun get(id: UUID): Mercadoria =
        service.get(id)

    fun create(mercadoriaCreateForm: MercadoriaCreateForm) =
        service.create(mercadoriaCreateForm)

    fun update(id: UUID, mercadoriaCreateForm: MercadoriaCreateForm) =
        service.update(id, mercadoriaCreateForm)

    fun increaseQuantidade(id: UUID, quantidade: Double) =
        service.increaseQuantidade(id, quantidade)

    fun delete(id: UUID) =
        service.delete(id)
}
