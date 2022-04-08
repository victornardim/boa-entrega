package boa.entrega.orders.model.slim

import java.util.UUID

data class RequisicaoSlim(
    val requisicaoId: UUID,
    val pedidoId: UUID
)
