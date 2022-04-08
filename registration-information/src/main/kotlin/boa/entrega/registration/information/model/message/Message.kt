package boa.entrega.registration.information.model.message

import java.time.Instant

data class Message<T>(
    val body: T,
    val createdAt: String = Instant.now().toString()
)