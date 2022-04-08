package boa.entrega.registration.information.exception.dto

import java.time.Clock
import java.time.Instant

data class ResponseError(
    val message: String,
    val timestamp: Instant? = Instant.now(Clock.systemUTC())
)
