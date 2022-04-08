package boa.entrega.orders.model.domain

import java.io.Serializable
import java.time.Instant
import java.util.UUID

data class Andamento(
    val id: UUID,
    val data: Instant,
    val status: AndamentoStatus
) : Serializable
