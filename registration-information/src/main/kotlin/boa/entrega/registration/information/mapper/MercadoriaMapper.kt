package boa.entrega.registration.information.mapper

import boa.entrega.registration.information.model.domain.MercadoriaTipo
import boa.entrega.registration.information.model.slim.MercadoriaSlim
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Component
import java.sql.ResultSet
import java.util.UUID

@Component
class MercadoriaMapper : RowMapper<MercadoriaSlim> {
    override fun mapRow(resultSet: ResultSet, rowNum: Int): MercadoriaSlim =
        MercadoriaSlim(
            id = UUID.fromString(resultSet.getString("id")),
            nome = resultSet.getString("nome"),
            fornecedorId = UUID.fromString(resultSet.getString("fornecedor_id")),
            depositoId = UUID.fromString(resultSet.getString("deposito_id")),
            quantidade = resultSet.getDouble("quantidade"),
            codigoBarras = resultSet.getString("codigo_barras"),
            tipo = MercadoriaTipo.valueOf(resultSet.getString("tipo")),
            dataValidade = resultSet.getDate("data_validade")
        )
}
