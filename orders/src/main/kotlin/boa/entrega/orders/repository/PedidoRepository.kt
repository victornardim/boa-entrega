package boa.entrega.orders.repository

import boa.entrega.orders.mapper.PedidoMapper
import boa.entrega.orders.model.domain.AndamentoStatus
import boa.entrega.orders.model.slim.PedidoSlim
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class PedidoRepository(
    private val jdbcOperations: NamedParameterJdbcOperations,
    private val mapper: PedidoMapper
) {
    companion object {
        private val LIST_BY_ANDAMENTO = this::class.java.getResource("/queries/pedido/list-by-andamento.sql").readText()
        private val LIST_BY_CLIENTE = this::class.java.getResource("/queries/pedido/list-by-cliente.sql").readText()
        private val GET = this::class.java.getResource("/queries/pedido/get.sql").readText()
        private val GET_BY_CODIGO_RASTREIO = this::class.java.getResource("/queries/pedido/get-by-codigo-rastreio.sql").readText()
        private val CREATE = this::class.java.getResource("/queries/pedido/create.sql").readText()
    }

    fun listByAndamento(andamento: AndamentoStatus, offset: Int, limit: Int): Collection<PedidoSlim> =
        jdbcOperations.query(
            LIST_BY_ANDAMENTO,
            mapOf(
                "status" to andamento.name,
                "offset" to offset,
                "limit" to limit
            ),
            mapper
        )

    fun listByCliente(clienteId: UUID, offset: Int, limit: Int): Collection<PedidoSlim> =
        jdbcOperations.query(
            LIST_BY_CLIENTE,
            mapOf(
                "clienteId" to clienteId,
                "offset" to offset,
                "limit" to limit
            ),
            mapper
        )

    fun get(id: UUID): PedidoSlim =
        jdbcOperations.query(
            GET,
            mapOf("id" to id),
            mapper
        ).first()

    fun getByCodigoRastreio(codigoRastreio: String): PedidoSlim =
        jdbcOperations.query(
            GET_BY_CODIGO_RASTREIO,
            mapOf("codigoRastreio" to codigoRastreio),
            mapper
        ).first()

    fun create(pedido: PedidoSlim) =
        jdbcOperations.update(
            CREATE,
            mapOf(
                "id" to pedido.id,
                "codigoRastreio" to pedido.codigoRastreio,
                "clienteId" to pedido.clienteId
            )
        )
}
