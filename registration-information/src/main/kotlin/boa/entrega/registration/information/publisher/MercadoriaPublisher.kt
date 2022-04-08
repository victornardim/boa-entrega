package boa.entrega.registration.information.publisher

import boa.entrega.registration.information.model.message.MercadoriaMessage
import boa.entrega.registration.information.model.message.MercadoriaUpdateQuantidadeBatchMessage
import boa.entrega.registration.information.model.message.Message
import boa.entrega.registration.information.model.message.Reply
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class MercadoriaPublisher(
    private val publisher: Publisher,
    @Value("\${messaging.topics.mercadoria}") private val TOPIC: String,
    @Value("\${messaging.queues.mercadoria.create.routing-key}") private val ROUTING_KEY_CREATE: String,
    @Value("\${messaging.queues.mercadoria.update.routing-key}") private val ROUTING_KEY_UPDATE: String,
    @Value("\${messaging.queues.mercadoria.update-quantidade.routing-key}") private val ROUTING_KEY_UPDATE_QUANTIDADE: String,
    @Value("\${messaging.queues.mercadoria.update-quantidade-reply.routing-key}") private val ROUTING_KEY_UPDATE_REPLY_QUANTIDADE: String,
    @Value("\${messaging.queues.mercadoria.delete.routing-key}") private val ROUTING_KEY_DELETE: String
) {

    fun publishCreate(message: Message<MercadoriaMessage>) {
        publisher.publish(TOPIC, ROUTING_KEY_CREATE, message)
    }

    fun publishUpdate(message: Message<MercadoriaMessage>) {
        publisher.publish(TOPIC, ROUTING_KEY_UPDATE, message)
    }

    fun publishQuantidadeDecrease(message: Message<MercadoriaUpdateQuantidadeBatchMessage>) {
        publisher.publish(TOPIC, ROUTING_KEY_UPDATE_QUANTIDADE, message)
    }

    fun publishQuantidadeDecreaseReply(message: Message<Reply<Unit>>) {
        publisher.publish(TOPIC, ROUTING_KEY_UPDATE_REPLY_QUANTIDADE, message)
    }

    fun publishDelete(message: Message<MercadoriaMessage>) {
        publisher.publish(TOPIC, ROUTING_KEY_DELETE, message)
    }
}
