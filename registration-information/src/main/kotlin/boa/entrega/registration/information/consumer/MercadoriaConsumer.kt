package boa.entrega.registration.information.consumer

import boa.entrega.registration.information.facade.MercadoriaBatchFacade
import boa.entrega.registration.information.model.message.MercadoriaUpdateQuantidadeBatchMessage
import boa.entrega.registration.information.model.message.Message
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class MercadoriaConsumer(
    @Value("\${messaging.queues.mercadoria.update-quantidade.name}") private val QUEUE: String,
    private val facade: MercadoriaBatchFacade,
    private val objectMapper: ObjectMapper
) : Consumer {
    override val queue = QUEUE

    override fun process(message: String) {
        val receivedMessage = objectMapper.readValue(message, object : TypeReference<Message<MercadoriaUpdateQuantidadeBatchMessage>>() {})
        val updateQuantidadeBatchForms = receivedMessage.body
        facade.decreaseQuantidade(
            updateQuantidadeBatchForms.requisicaoId,
            updateQuantidadeBatchForms.forms
        )
    }
}
