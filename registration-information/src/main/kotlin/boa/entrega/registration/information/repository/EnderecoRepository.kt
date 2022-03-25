package boa.entrega.registration.information.repository

import boa.entrega.registration.information.mapper.EnderecoMapper
import boa.entrega.registration.information.model.domain.Endereco
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class EnderecoRepository(
    private val jdbcOperations: NamedParameterJdbcOperations,
    private val mapper: EnderecoMapper
) {
    companion object {
        private val GET = this::class.java.getResource("/queries/endereco/get.sql").readText()
    }

    fun get(id: UUID): Endereco =
        jdbcOperations.query(
            GET,
            mapOf("id" to id),
            mapper
        ).first()
}