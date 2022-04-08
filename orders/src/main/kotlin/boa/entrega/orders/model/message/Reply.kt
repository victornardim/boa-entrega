package boa.entrega.orders.model.message

import java.util.UUID

data class Reply<T>(
    val requestId: UUID,
    val status: ReplyStatus,
    val content: T? = null
)
