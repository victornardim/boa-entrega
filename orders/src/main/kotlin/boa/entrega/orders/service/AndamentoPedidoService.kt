package boa.entrega.orders.service

import boa.entrega.orders.model.domain.Andamento
import boa.entrega.orders.repository.PedidoAndamentoRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class AndamentoPedidoService(
    private val repository: PedidoAndamentoRepository
) {
    fun listByPedido(pedidoId: UUID): Collection<Andamento> =
        repository.listByPedido(pedidoId)

    fun getLastByPedido(pedidoId: UUID): Andamento =
        repository.getLastByPedido(pedidoId)

    fun create(pedidoId: UUID, andamento: Andamento) {
        repository.create(pedidoId, andamento)
    }
}
