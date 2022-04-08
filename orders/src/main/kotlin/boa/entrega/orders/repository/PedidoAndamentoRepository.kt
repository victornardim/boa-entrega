package boa.entrega.orders.repository

import boa.entrega.orders.mapper.AndamentoMapper
import boa.entrega.orders.model.domain.Andamento
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class PedidoAndamentoRepository(
    private val jdbcOperations: NamedParameterJdbcOperations,
    private val mapper: AndamentoMapper
) {
    companion object {
        private val LIST_BY_PEDIDO = this::class.java.getResource("/queries/pedido/andamento/list-by-pedido.sql").readText()
        private val GET_LAST_BY_PEDIDO = this::class.java.getResource("/queries/pedido/andamento/get-last-by-pedido.sql").readText()
        private val CREATE = this::class.java.getResource("/queries/pedido/andamento/create.sql").readText()
    }

    fun listByPedido(pedidoId: UUID): Collection<Andamento> =
        jdbcOperations.query(
            LIST_BY_PEDIDO,
            mapOf("pedidoId" to pedidoId),
            mapper
        )

    fun getLastByPedido(pedidoId: UUID): Andamento =
        jdbcOperations.query(
            GET_LAST_BY_PEDIDO,
            mapOf("pedidoId" to pedidoId),
            mapper
        ).last()

    fun create(pedidoId: UUID, andamento: Andamento) =
        jdbcOperations.update(
            CREATE,
            mapOf(
                "id" to andamento.id,
                "pedidoId" to pedidoId,
                "status" to andamento.status.name
            )
        )
}
