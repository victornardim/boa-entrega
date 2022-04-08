package boa.entrega.orders.mapper

import boa.entrega.orders.model.slim.RequisicaoSlim
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Component
import java.sql.ResultSet
import java.util.UUID

@Component
class RequisicaoMapper : RowMapper<RequisicaoSlim> {
    override fun mapRow(resultSet: ResultSet, rowNum: Int): RequisicaoSlim =
        RequisicaoSlim(
            requisicaoId = UUID.fromString(resultSet.getString("requisicao_id")),
            pedidoId = UUID.fromString(resultSet.getString("pedido_id"))
        )
}
