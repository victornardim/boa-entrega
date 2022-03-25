package boa.entrega.registration.information.repository

import boa.entrega.registration.information.mapper.ClienteMapper
import boa.entrega.registration.information.model.slim.ClienteSlim
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class ClienteRepository(
    private val jdbcOperations: NamedParameterJdbcOperations,
    private val mapper: ClienteMapper
) {
    companion object {
        private val LIST = this::class.java.getResource("/queries/cliente/list.sql").readText()
        private val GET = this::class.java.getResource("/queries/cliente/get.sql").readText()
    }

    fun list(offset: Int, limit: Int): Collection<ClienteSlim> =
        jdbcOperations.query(
            LIST,
            mapOf(
                "offset" to offset,
                "limit" to limit
            ),
            mapper
        )

    fun get(id: UUID): ClienteSlim =
        jdbcOperations.query(
            GET,
            mapOf("id" to id),
            mapper
        ).first()
}