package boa.entrega.registration.information.publisher

import boa.entrega.registration.information.model.domain.Event
import boa.entrega.registration.information.model.slim.MercadoriaSlim
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class MercadoriaPublisher(
    private val publisher: Publisher,
    @Value("\${messaging.topics.mercadoria}") private val TOPIC: String,
    @Value("\${messaging.queues.mercadoria.create.routing-key}") private val ROUTING_KEY_CREATE: String,
    @Value("\${messaging.queues.mercadoria.update.routing-key}") private val ROUTING_KEY_UPDATE: String,
    @Value("\${messaging.queues.mercadoria.delete.routing-key}") private val ROUTING_KEY_DELETE: String
) {

    fun publishCreate(event: Event<MercadoriaSlim>) {
        publisher.publish(TOPIC, ROUTING_KEY_CREATE, event)
    }

    fun publishUpdate(event: Event<MercadoriaSlim>) {
        publisher.publish(TOPIC, ROUTING_KEY_UPDATE, event)
    }

    fun publishDelete(event: Event<MercadoriaSlim>) {
        publisher.publish(TOPIC, ROUTING_KEY_DELETE, event)
    }
}