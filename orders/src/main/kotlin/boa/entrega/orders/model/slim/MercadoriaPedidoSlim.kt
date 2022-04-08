package boa.entrega.orders.model.slim

import java.util.UUID

data class MercadoriaPedidoSlim(
    val pedidoId: UUID,
    val mercadoriaId: UUID,
    val quantidade: Double
)
