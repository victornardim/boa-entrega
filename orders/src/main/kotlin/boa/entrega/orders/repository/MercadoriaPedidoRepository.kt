package boa.entrega.orders.repository

import boa.entrega.orders.mapper.PedidoMercadoriaMapper
import boa.entrega.orders.model.slim.MercadoriaPedidoSlim
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class MercadoriaPedidoRepository(
    private val jdbcOperations: NamedParameterJdbcOperations,
    private val mapper: PedidoMercadoriaMapper
) {
    companion object {
        private val LIST_BY_PEDIDO = this::class.java.getResource("/queries/pedido/mercadoria/list-by-pedido.sql").readText()
        private val CREATE = this::class.java.getResource("/queries/pedido/mercadoria/create.sql").readText()
    }

    fun listByPedido(pedidoId: UUID): Collection<MercadoriaPedidoSlim> =
        jdbcOperations.query(
            LIST_BY_PEDIDO,
            mapOf("pedidoId" to pedidoId),
            mapper
        )

    fun create(mercadoriaPedidoSlim: MercadoriaPedidoSlim) =
        jdbcOperations.update(
            CREATE,
            mapOf(
                "pedidoId" to mercadoriaPedidoSlim.pedidoId,
                "mercadoriaId" to mercadoriaPedidoSlim.mercadoriaId,
                "quantidade" to mercadoriaPedidoSlim.quantidade
            )
        )
}
