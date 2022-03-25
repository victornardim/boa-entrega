package boa.entrega.registration.information.repository

import boa.entrega.registration.information.mapper.DepositoMapper
import boa.entrega.registration.information.model.slim.DepositoSlim
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class DepositoRepository(
    private val jdbcOperations: NamedParameterJdbcOperations,
    private val mapper: DepositoMapper
) {
    companion object {
        private val LIST = this::class.java.getResource("/queries/deposito/list.sql").readText()
        private val GET = this::class.java.getResource("/queries/deposito/get.sql").readText()
    }

    fun list(offset: Int, limit: Int): Collection<DepositoSlim> =
        jdbcOperations.query(
            LIST,
            mapOf(
                "offset" to offset,
                "limit" to limit
            ),
            mapper
        )

    fun get(id: UUID): DepositoSlim =
        jdbcOperations.query(
            GET,
            mapOf(
                "id" to id
            ),
            mapper
        ).first()
}