package boa.entrega.registration.information.publisher

import boa.entrega.registration.information.model.domain.Event
import com.rabbitmq.client.ConnectionFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class Publisher(
    @Value("\${messaging.host}") private val HOST: String,
    @Value("\${messaging.port}") private val PORT: Int,
    @Value("\${messaging.username}") private val USERNAME: String,
    @Value("\${messaging.password}") private val PASSWORD: String
) {
    fun <T: Any> publish(topic: String, routingKey: String, event: Event<T>) {
        val factory = ConnectionFactory()
        factory.host = HOST
        factory.port = PORT
        factory.username = USERNAME
        factory.password = PASSWORD

        factory.newConnection().use { connection ->
            connection.createChannel().use { channel ->
                channel.basicPublish(topic, routingKey, null, event.toMessage())
            }
        }
    }
}