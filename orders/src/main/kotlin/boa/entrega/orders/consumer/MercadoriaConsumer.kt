package boa.entrega.orders.consumer

import boa.entrega.orders.model.message.Message
import boa.entrega.orders.model.message.Reply
import boa.entrega.orders.model.message.ReplyStatus
import boa.entrega.orders.service.PedidoService
import boa.entrega.orders.service.RequisicaoPedidoService
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class MercadoriaConsumer(
    @Value("\${messaging.queues.mercadoria.update-quantidade-reply.name}") private val QUEUE: String,
    private val requisicaoPedidoService: RequisicaoPedidoService,
    private val pedidoService: PedidoService,
    private val objectMapper: ObjectMapper
) : Consumer {
    override val queue = QUEUE

    @Transactional
    override fun process(message: String) {
        val receivedMessage = objectMapper.readValue(message, object: TypeReference<Message<Reply<Unit>>>() {})
        val reply = receivedMessage.body

        val requisicao = requisicaoPedidoService.get(reply.requestId)

        when (reply.status) {
            ReplyStatus.SUCCESS -> pedidoService.finishCreate(requisicao.pedidoId)
            ReplyStatus.FAILURE -> pedidoService.abort(requisicao.pedidoId)
        }

        requisicaoPedidoService.delete(requisicao.requisicaoId)
    }
}
