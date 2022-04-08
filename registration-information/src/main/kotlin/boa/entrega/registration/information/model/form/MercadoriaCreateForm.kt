package boa.entrega.registration.information.model.form

import boa.entrega.registration.information.model.domain.MercadoriaTipo
import boa.entrega.registration.information.model.slim.MercadoriaSlim
import java.util.Date
import java.util.UUID
import javax.validation.constraints.DecimalMin
import javax.validation.constraints.NotEmpty

data class MercadoriaCreateForm(
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
    fun toSlim(id: UUID): MercadoriaSlim =
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
