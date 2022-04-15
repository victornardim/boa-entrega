package boa.entrega.orders.publisher

import boa.entrega.orders.model.message.MercadoriaDecreaseQuantidadeMessage
import boa.entrega.orders.model.message.Message
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class MercadoriaPublisher(
    private val publisher: Publisher,
    @Value("\${messaging.topics.mercadoria}") private val TOPIC: String,
    @Value("\${messaging.queues.mercadoria.update-quantidade.routing-key}") private val ROUTING_KEY_UPDATE_QUANTIDADE: String
) {
    fun publishDecreaseQuantidade(message: Message<MercadoriaDecreaseQuantidadeMessage>) {
        publisher.publish(TOPIC, ROUTING_KEY_UPDATE_QUANTIDADE, message)
    }
}
