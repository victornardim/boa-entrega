package boa.entrega.orders.repository

import boa.entrega.orders.mapper.RequisicaoMapper
import boa.entrega.orders.model.slim.RequisicaoSlim
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class RequisicaoPedidoRepository(
    private val jdbcOperations: NamedParameterJdbcOperations,
    private val mapper: RequisicaoMapper
) {
    companion object {
        private val GET = this::class.java.getResource("/queries/pedido/requisicao/get.sql").readText()
        private val CREATE = this::class.java.getResource("/queries/pedido/requisicao/create.sql").readText()
        private val DELETE = this::class.java.getResource("/queries/pedido/requisicao/delete.sql").readText()
    }

    fun get(id: UUID): RequisicaoSlim =
        jdbcOperations.query(
            GET,
            mapOf("requisicaoId" to id),
            mapper
        ).first()

    fun create(requisicaoSlim: RequisicaoSlim) =
        jdbcOperations.update(
            CREATE,
            mapOf(
                "requisicaoId" to requisicaoSlim.requisicaoId,
                "pedidoId" to requisicaoSlim.pedidoId
            )
        )

    fun delete(id: UUID) =
        jdbcOperations.update(
            DELETE,
            mapOf("requisicaoId" to id)
        )
}
