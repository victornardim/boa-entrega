package boa.entrega.registration.information.mapper

import boa.entrega.registration.information.model.domain.Endereco
import boa.entrega.registration.information.model.domain.EnderecoTipo
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Component
import java.sql.ResultSet
import java.util.UUID

@Component
class EnderecoMapper : RowMapper<Endereco> {
    override fun mapRow(resultSet: ResultSet, rowNum: Int): Endereco = Endereco(
        id = UUID.fromString(resultSet.getString("id")),
        tipo = EnderecoTipo.valueOf(resultSet.getString("tipo")),
        logradouro = resultSet.getString("logradouro"),
        numero = resultSet.getString("numero"),
        complemento = resultSet.getString("complemento"),
        bairro = resultSet.getString("bairro"),
        cidade = resultSet.getString("cidade"),
        uf = resultSet.getString("uf"),
        cep = resultSet.getString("cep"),
        latitude = resultSet.getDouble("latitude"),
        longitude = resultSet.getDouble("longitude")
    )
}
