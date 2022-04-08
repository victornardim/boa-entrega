package boa.entrega.registration.information.model.form

import javax.validation.constraints.DecimalMin
import javax.validation.constraints.NotEmpty

data class MercadoriaUpdateQuantidadeForm(
    @NotEmpty
    @DecimalMin("0.0")
    val quantidade: Double
)
