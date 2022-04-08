package boa.entrega.orders.mapper

import boa.entrega.orders.model.domain.Andamento
import boa.entrega.orders.model.domain.AndamentoStatus
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Component
import java.sql.ResultSet
import java.util.UUID

@Component
class AndamentoMapper : RowMapper<Andamento> {
    override fun mapRow(resultSet: ResultSet, rowNum: Int): Andamento =
        Andamento(
            id = UUID.fromString(resultSet.getString("id")),
            data = resultSet.getTimestamp("data").toInstant(),
            status = AndamentoStatus.valueOf(resultSet.getString("status"))
        )
}
