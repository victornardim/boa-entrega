package boa.entrega.orders.mapper

import boa.entrega.orders.model.slim.MercadoriaPedidoSlim
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Component
import java.sql.ResultSet
import java.util.UUID

@Component
class PedidoMercadoriaMapper : RowMapper<MercadoriaPedidoSlim> {
    override fun mapRow(resultSet: ResultSet, rowNum: Int): MercadoriaPedidoSlim =
        MercadoriaPedidoSlim(
            pedidoId = UUID.fromString(resultSet.getString("pedido_id")),
            mercadoriaId = UUID.fromString(resultSet.getString("mercadoria_id")),
            quantidade = resultSet.getDouble("quantidade")
        )
}
