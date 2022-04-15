package boa.entrega.orders.model.form

import java.util.UUID

data class MercadoriaDecreaseQuantidadeForm(
    val id: UUID,
    val quantidade: Double
)
