package boa.entrega.registration.information.facade

import boa.entrega.registration.information.exception.dto.BadRequestException
import boa.entrega.registration.information.model.form.MercadoriaUpdateQuantidadeBatchForm
import boa.entrega.registration.information.model.message.MercadoriaUpdateQuantidadeBatchMessage
import boa.entrega.registration.information.model.message.Reply
import boa.entrega.registration.information.model.message.ReplyStatus
import boa.entrega.registration.information.publisher.MercadoriaPublisher
import boa.entrega.registration.information.service.MercadoriaService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class MercadoriaBatchFacade(
    private val publisher: MercadoriaPublisher,
    private val service: MercadoriaService
) {
    fun createDecreaseQuantidadeRequest(updateQuantidadeBatchForms: Collection<MercadoriaUpdateQuantidadeBatchForm>): UUID {
        val requisicaoId = UUID.randomUUID()

        publisher.publishQuantidadeDecrease(
            MercadoriaUpdateQuantidadeBatchMessage(
                requisicaoId,
                updateQuantidadeBatchForms
            ).toMessage()
        )

        return requisicaoId
    }

    @Transactional
    fun decreaseQuantidade(
        requisicaoId: UUID,
        updateQuantidadeBatchForms: Collection<MercadoriaUpdateQuantidadeBatchForm>
    ) {
        try {
            updateQuantidadeBatchForms.forEach {
                service.decreaseQuantidade(it.id, it.quantidade)
            }

            publisher.publishQuantidadeDecreaseReply(
                Reply<Unit>(
                    requestId = requisicaoId,
                    status = ReplyStatus.SUCCESS
                ).toMessage()
            )
        } catch (ex: BadRequestException) {
            publisher.publishQuantidadeDecreaseReply(
                Reply<Unit>(
                    requestId = requisicaoId,
                    status = ReplyStatus.FAILURE
                ).toMessage()
            )

            throw ex
        }
    }
}
