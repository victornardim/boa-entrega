package boa.entrega.registration.information.repository

import boa.entrega.registration.information.mapper.MercadoriaMapper
import boa.entrega.registration.information.model.slim.MercadoriaSlim
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class MercadoriaRepository(
    private val jdbcOperations: NamedParameterJdbcOperations,
    private val mapper: MercadoriaMapper
) {
    companion object {
        private val LIST_BY_SUPPLIER = this::class.java.getResource("/queries/mercadoria/list-by-fornecedor.sql").readText()
        private val LIST_BY_WAREHOUSE = this::class.java.getResource("/queries/mercadoria/list-by-deposito.sql").readText()
        private val GET = this::class.java.getResource("/queries/mercadoria/get.sql").readText()
        private val CREATE = this::class.java.getResource("/queries/mercadoria/create.sql").readText()
        private val UPDATE = this::class.java.getResource("/queries/mercadoria/update.sql").readText()
        private val UPDATE_QUANTITY = this::class.java.getResource("/queries/mercadoria/update-quantidade.sql").readText()
        private val DELETE = this::class.java.getResource("/queries/mercadoria/delete.sql").readText()
    }

    fun listByFornecedor(fornecedorId: UUID, offset: Int, limit: Int): Collection<MercadoriaSlim> =
        jdbcOperations.query(
            LIST_BY_SUPPLIER,
            mapOf(
                "fornecedorId" to fornecedorId,
                "offset" to offset,
                "limit" to limit
            ),
            mapper
        )

    fun listByDeposito(depositoId: UUID, offset: Int, limit: Int): Collection<MercadoriaSlim> =
        jdbcOperations.query(
            LIST_BY_WAREHOUSE,
            mapOf(
                "depositoId" to depositoId,
                "offset" to offset,
                "limit" to limit
            ),
            mapper
        )

    fun get(id: UUID): MercadoriaSlim =
        jdbcOperations.query(
            GET,
            mapOf("id" to id),
            mapper
        ).first()

    fun create(mercadoriaSlim: MercadoriaSlim) =
        jdbcOperations.update(
            CREATE,
            mapOf(
                "id" to mercadoriaSlim.id,
                "fornecedorId" to mercadoriaSlim.fornecedorId,
                "depositoId" to mercadoriaSlim.depositoId,
                "nome" to mercadoriaSlim.nome,
                "tipo" to mercadoriaSlim.tipo.name,
                "codigoBarras" to mercadoriaSlim.codigoBarras,
                "dataValidade" to mercadoriaSlim.dataValidade,
                "quantidade" to mercadoriaSlim.quantidade
            )
        )

    fun update(mercadoriaSlim: MercadoriaSlim) =
        jdbcOperations.update(
            UPDATE,
            mapOf(
                "id" to mercadoriaSlim.id,
                "fornecedorId" to mercadoriaSlim.fornecedorId,
                "depositoId" to mercadoriaSlim.depositoId,
                "nome" to mercadoriaSlim.nome,
                "tipo" to mercadoriaSlim.tipo.name,
                "codigoBarras" to mercadoriaSlim.codigoBarras,
                "dataValidade" to mercadoriaSlim.dataValidade,
                "quantidade" to mercadoriaSlim.quantidade
            )
        )

    fun updateQuantity(id: UUID, quantidade: Double) =
        jdbcOperations.update(
            UPDATE_QUANTITY,
            mapOf(
                "id" to id,
                "quantidade" to quantidade
            )
        )

    fun delete(id: UUID) =
        jdbcOperations.update(
            DELETE,
            mapOf("id" to id)
        )
}