package boa.entrega.orders.configuration

import boa.entrega.orders.consumer.Consumer
import boa.entrega.orders.consumer.Subscriber
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class QueueConsumerConfiguration(
    private val consumers: Collection<Consumer>,
    private val subscriber: Subscriber
) {
    @EventListener(ApplicationReadyEvent::class)
    fun subscribe() {
        consumers.forEach {
            subscriber.subscribe(it.queue, it::process)
        }
    }
}
