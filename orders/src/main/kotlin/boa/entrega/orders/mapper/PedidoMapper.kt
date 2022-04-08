package boa.entrega.orders.mapper

import boa.entrega.orders.model.slim.PedidoSlim
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Component
import java.sql.ResultSet
import java.util.UUID

@Component
class PedidoMapper : RowMapper<PedidoSlim> {
    override fun mapRow(resultSet: ResultSet, rowNum: Int): PedidoSlim =
        PedidoSlim(
            id = UUID.fromString(resultSet.getString("id")),
            codigoRastreio = resultSet.getString("codigo_rastreio"),
            clienteId = UUID.fromString(resultSet.getString("cliente_id")),
            data = resultSet.getTimestamp("data").toInstant()
        )
}
