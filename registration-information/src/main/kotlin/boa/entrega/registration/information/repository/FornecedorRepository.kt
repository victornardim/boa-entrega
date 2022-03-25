package boa.entrega.registration.information.repository

import boa.entrega.registration.information.mapper.FornecedorMapper
import boa.entrega.registration.information.model.slim.FornecedorSlim
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class FornecedorRepository(
    private val jdbcOperations: NamedParameterJdbcOperations,
    private val mapper: FornecedorMapper
) {
    companion object {
        private val LIST = this::class.java.getResource("/queries/fornecedor/list.sql").readText()
        private val GET = this::class.java.getResource("/queries/fornecedor/get.sql").readText()
    }

    fun list(offset: Int, limit: Int): Collection<FornecedorSlim> =
        jdbcOperations.query(
            LIST,
            mapOf(
                "offset" to offset,
                "limit" to limit
            ),
            mapper
        )

    fun get(id: UUID): FornecedorSlim =
        jdbcOperations.query(
            GET,
            mapOf("id" to id),
            mapper
        ).first()
}