package boa.entrega.orders.model.message

import java.time.Instant
import java.util.UUID

data class Message<T>(
    val requestId: UUID? = null,
    val body: T,
    val createdAt: String = Instant.now().toString()
)
