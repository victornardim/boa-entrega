package boa.entrega.orders.publisher

import boa.entrega.orders.model.message.Message
import com.fasterxml.jackson.databind.ObjectMapper
import com.rabbitmq.client.ConnectionFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class Publisher(
    @Value("\${messaging.host}") private val HOST: String,
    @Value("\${messaging.port}") private val PORT: Int,
    @Value("\${messaging.username}") private val USERNAME: String,
    @Value("\${messaging.password}") private val PASSWORD: String,
    private val objectMapper: ObjectMapper
) {
    fun <T : Any> publish(topic: String, routingKey: String, message: Message<T>) {
        val factory = ConnectionFactory()
        factory.host = HOST
        factory.port = PORT
        factory.username = USERNAME
        factory.password = PASSWORD

        factory.newConnection().use { connection ->
            connection.createChannel().use { channel ->
                channel.basicPublish(
                    topic,
                    routingKey,
                    null,
                    objectMapper.writeValueAsString(message).toByteArray(Charsets.UTF_8)
                )
            }
        }
    }
}
