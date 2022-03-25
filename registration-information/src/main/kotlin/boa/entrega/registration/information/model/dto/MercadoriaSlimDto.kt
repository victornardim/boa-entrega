package boa.entrega.registration.information.model.dto

import boa.entrega.registration.information.model.slim.MercadoriaSlim
import boa.entrega.registration.information.model.domain.MercadoriaTipo
import java.time.Instant
import java.util.UUID
import java.util.Date
import javax.validation.constraints.DecimalMin
import javax.validation.constraints.NotEmpty

data class MercadoriaSlimDto(
    @NotEmpty
    val nome: String,

    @NotEmpty
    val fornecedorId: UUID,

    @NotEmpty
    val depositoId: UUID,

    @NotEmpty
    @DecimalMin("0.0")
    val quantidade: Double,

    @NotEmpty
    val codigoBarras: String,

    @NotEmpty
    val tipo: MercadoriaTipo,

    @NotEmpty
    val dataValidade: Date
) {
    fun toDomain(id: UUID): MercadoriaSlim =
        MercadoriaSlim(
            id = id,
            nome = nome,
            fornecedorId = fornecedorId,
            depositoId = depositoId,
            quantidade = quantidade,
            codigoBarras = codigoBarras,
            tipo = tipo,
            dataValidade = dataValidade
        )
}