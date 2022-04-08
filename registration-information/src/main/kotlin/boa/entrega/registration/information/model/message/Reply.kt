package boa.entrega.registration.information.model.message

import java.util.UUID

data class Reply<T>(
    val requestId: UUID,
    val status: ReplyStatus,
    val content: T? = null
) {
    fun toMessage(): Message<Reply<T>> =
        Message(
            body = this
        )
}
