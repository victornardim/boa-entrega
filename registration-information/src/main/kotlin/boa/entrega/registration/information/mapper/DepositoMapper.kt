package boa.entrega.registration.information.mapper

import boa.entrega.registration.information.model.domain.DepositoCapacidadeTipo
import boa.entrega.registration.information.model.domain.DepositoTipo
import boa.entrega.registration.information.model.slim.DepositoSlim
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Component
import java.sql.ResultSet
import java.util.UUID

@Component
class DepositoMapper : RowMapper<DepositoSlim> {
    override fun mapRow(resultSet: ResultSet, rowNum: Int): DepositoSlim =
        DepositoSlim(
            id = UUID.fromString(resultSet.getString("id")),
            enderecoId = UUID.fromString(resultSet.getString("endereco_id")),
            tipo = DepositoTipo.valueOf(resultSet.getString("tipo")),
            capacidade = resultSet.getDouble("capacidade"),
            capacidadeTipo = DepositoCapacidadeTipo.valueOf(resultSet.getString("capacidade_tipo"))
        )
}
