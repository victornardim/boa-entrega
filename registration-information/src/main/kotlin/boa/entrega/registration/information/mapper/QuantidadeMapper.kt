package boa.entrega.registration.information.mapper

import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Component
import java.sql.ResultSet

@Component
class QuantidadeMapper : RowMapper<Double> {
    override fun mapRow(resultSet: ResultSet, rowNum: Int): Double = resultSet.getDouble("quantidade")
}
