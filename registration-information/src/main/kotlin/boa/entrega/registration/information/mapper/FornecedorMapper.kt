package boa.entrega.registration.information.mapper

import boa.entrega.registration.information.model.slim.FornecedorSlim
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Component
import java.sql.ResultSet
import java.util.UUID

@Component
class FornecedorMapper: RowMapper<FornecedorSlim> {
    override fun mapRow(resultSet: ResultSet, rowNum: Int): FornecedorSlim =
        FornecedorSlim(
            id = UUID.fromString(resultSet.getString("id")),
            razaoSocial = resultSet.getString("razao_social"),
            nomeFantasia = resultSet.getString("nome_fantasia"),
            cnpj = resultSet.getString("cnpj"),
            enderecoId = UUID.fromString(resultSet.getString("endereco_id"))
        )
}