package boa.entrega.orders.model.form

import java.util.UUID

data class MercadoriaUpdateQuantidadeBatchForm(
    val id: UUID,
    val quantidade: Double
)
