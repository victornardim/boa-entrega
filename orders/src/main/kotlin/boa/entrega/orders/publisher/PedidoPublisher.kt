package boa.entrega.orders.publisher

import boa.entrega.orders.model.message.Message
import boa.entrega.orders.model.message.PedidoMessage
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class PedidoPublisher(
    private val publisher: Publisher,
    @Value("\${messaging.topics.pedido}") private val TOPIC: String,
    @Value("\${messaging.queues.pedido.create.routing-key}") private val ROUTING_KEY_CREATE: String,
    @Value("\${messaging.queues.pedido.next-step.routing-key}") private val ROUTING_KEY_NEXT_STEP: String,
    @Value("\${messaging.queues.pedido.cancel.routing-key}") private val ROUTING_KEY_CANCEL: String
) {
    fun publishCreate(message: Message<PedidoMessage>) {
        publisher.publish(TOPIC, ROUTING_KEY_CREATE, message)
    }

    fun publishNextStep(message: Message<PedidoMessage>) {
        publisher.publish(TOPIC, ROUTING_KEY_NEXT_STEP, message)
    }

    fun publishCancel(message: Message<PedidoMessage>) {
        publisher.publish(TOPIC, ROUTING_KEY_CANCEL, message)
    }
}
