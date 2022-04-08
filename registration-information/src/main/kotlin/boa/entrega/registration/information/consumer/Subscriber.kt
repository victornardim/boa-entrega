package boa.entrega.registration.information.consumer

import com.rabbitmq.client.CancelCallback
import com.rabbitmq.client.Channel
import com.rabbitmq.client.Connection
import com.rabbitmq.client.ConnectionFactory
import com.rabbitmq.client.DeliverCallback
import com.rabbitmq.client.Delivery
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class Subscriber(
    @Value("\${messaging.host}") private val HOST: String,
    @Value("\${messaging.port}") private val PORT: Int,
    @Value("\${messaging.username}") private val USERNAME: String,
    @Value("\${messaging.password}") private val PASSWORD: String
) {
    private val logger = LoggerFactory.getLogger(Subscriber::class.java)

    fun subscribe(queue: String, process: (String) -> Unit) {
        val factory = ConnectionFactory()
        factory.host = HOST
        factory.port = PORT
        factory.username = USERNAME
        factory.password = PASSWORD

        val connection: Connection = factory.newConnection()
        val channel: Channel = connection.createChannel()

        val deliverCallback = DeliverCallback { _: String?, delivery: Delivery ->
            val message = String(delivery.body, Charsets.UTF_8)
            process(message)
        }
        val cancelCallback = CancelCallback { consumerTag: String? ->
            logger.warn("Queue $queue subscribe: $consumerTag was canceled")
        }

        channel.basicConsume(queue, true, deliverCallback, cancelCallback)
    }
}
