package boa.entrega.registration.information.mapper

import boa.entrega.registration.information.model.slim.ClienteSlim
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Component
import java.sql.ResultSet
import java.util.UUID

@Component
class ClienteMapper : RowMapper<ClienteSlim> {
    override fun mapRow(resultSet: ResultSet, rowNum: Int): ClienteSlim =
        ClienteSlim(
            id = UUID.fromString(resultSet.getString("id")),
            enderecoId = UUID.fromString(resultSet.getString("endereco_id")),
            name = resultSet.getString("nome"),
            cpf = resultSet.getString("cpf"),
            telefone = resultSet.getString("telefone"),
            email = resultSet.getString("email")
        )
}
