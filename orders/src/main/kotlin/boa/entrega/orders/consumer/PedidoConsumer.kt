package boa.entrega.orders.consumer

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class PedidoConsumer(
    @Value("\${messaging.queues.legacy.pedido.create.name}") private val QUEUE: String
) : Consumer {
    override val queue = QUEUE

    private val logger = LoggerFactory.getLogger(PedidoConsumer::class.java)

    override fun process(message: String) {
        logger.info("Message received: $message")
    }
}
