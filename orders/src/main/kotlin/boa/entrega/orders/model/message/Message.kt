package boa.entrega.orders.model.message

import java.time.Instant

data class Message<T>(
    val body: T,
    val createdAt: String = Instant.now().toString()
)
