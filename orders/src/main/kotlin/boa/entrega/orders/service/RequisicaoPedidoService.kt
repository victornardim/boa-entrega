package boa.entrega.orders.service

import boa.entrega.orders.model.slim.RequisicaoSlim
import boa.entrega.orders.repository.RequisicaoPedidoRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class RequisicaoPedidoService(
    private val repository: RequisicaoPedidoRepository
) {
    fun get(id: UUID): RequisicaoSlim =
        repository.get(id)

    fun create(requisicaoSlim: RequisicaoSlim) {
        repository.create(requisicaoSlim)
    }

    fun delete(id: UUID) {
        repository.delete(id)
    }
}
