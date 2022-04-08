package boa.entrega.orders.service

import boa.entrega.orders.model.form.MercadoriaPedidoCreateForm
import boa.entrega.orders.model.slim.MercadoriaPedidoSlim
import boa.entrega.orders.repository.MercadoriaPedidoRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class MercadoriaPedidoService(
    private val repository: MercadoriaPedidoRepository
) {
    fun listByPedido(pedidoId: UUID): Collection<MercadoriaPedidoSlim> =
        repository.listByPedido(pedidoId)

    fun create(pedidoId: UUID, mercadoriaPedidoCreateForm: MercadoriaPedidoCreateForm) {
        repository.create(mercadoriaPedidoCreateForm.toSlim(pedidoId))
    }
}
