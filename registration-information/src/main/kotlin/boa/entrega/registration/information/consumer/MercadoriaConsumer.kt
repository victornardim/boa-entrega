package boa.entrega.registration.information.consumer

import boa.entrega.registration.information.exception.dto.BadRequestException
import boa.entrega.registration.information.exception.dto.InvalidMessageException
import boa.entrega.registration.information.model.message.MercadoriaDecreaseQuantidadeMessage
import boa.entrega.registration.information.model.message.Message
import boa.entrega.registration.information.model.message.Reply
import boa.entrega.registration.information.model.message.ReplyStatus
import boa.entrega.registration.information.publisher.MercadoriaPublisher
import boa.entrega.registration.information.service.MercadoriaService
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class MercadoriaConsumer(
    @Value("\${messaging.queues.mercadoria.update-quantidade.name}") private val QUEUE: String,
    private val service: MercadoriaService,
    private val publisher: MercadoriaPublisher,
    private val objectMapper: ObjectMapper
) : Consumer {
    override val queue = QUEUE

    private val logger = LoggerFactory.getLogger(MercadoriaConsumer::class.java)

    override fun process(message: String) {
        val receivedMessage = objectMapper.readValue(message, object : TypeReference<Message<MercadoriaDecreaseQuantidadeMessage>>() {})
        val updateQuantidadeBatchForms = receivedMessage.body.forms

        try {
            updateQuantidadeBatchForms.forEach {
                service.decreaseQuantidade(it.id, it.quantidade)
            }

            publisher.publishQuantidadeDecreaseReply(
                Reply<Unit>(
                    requestId = receivedMessage.requestId ?: throw InvalidMessageException("Message without request id"),
                    status = ReplyStatus.SUCCESS
                ).toMessage()
            )
        } catch (ex: BadRequestException) {
            publisher.publishQuantidadeDecreaseReply(
                Reply<Unit>(
                    requestId = receivedMessage.requestId ?: throw InvalidMessageException("Message without request id"),
                    status = ReplyStatus.FAILURE
                ).toMessage()
            )

            throw ex
        } catch (ex: InvalidMessageException) {
            logger.error(ex.message)
        }
    }
}
