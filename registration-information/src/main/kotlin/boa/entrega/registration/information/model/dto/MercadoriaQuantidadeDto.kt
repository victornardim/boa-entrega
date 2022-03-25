package boa.entrega.registration.information.model.dto

import javax.validation.constraints.DecimalMin
import javax.validation.constraints.NotEmpty

data class MercadoriaQuantidadeDto(
    @NotEmpty
    @DecimalMin("0.0")
    val quantidade: Double
)