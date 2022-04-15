package boa.entrega.registration.information.model.form

import java.util.UUID
import javax.validation.constraints.DecimalMin
import javax.validation.constraints.NotEmpty

data class MercadoriaDecreaseQuantidadeForm(
    @NotEmpty
    val id: UUID,

    @NotEmpty
    @DecimalMin("0.0")
    val quantidade: Double
)
