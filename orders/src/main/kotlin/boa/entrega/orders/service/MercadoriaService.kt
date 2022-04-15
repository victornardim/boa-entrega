package boa.entrega.orders.service

import boa.entrega.orders.client.RegistrationInformationClient
import boa.entrega.orders.model.dto.MercadoriaDto
import boa.entrega.orders.model.form.MercadoriaDecreaseQuantidadeForm
import boa.entrega.orders.model.message.MercadoriaDecreaseQuantidadeMessage
import boa.entrega.orders.model.message.Message
import boa.entrega.orders.publisher.MercadoriaPublisher
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class MercadoriaService(
    private val registrationInformationClient: RegistrationInformationClient,
    private val publisher: MercadoriaPublisher
) {
    fun get(id: UUID): MercadoriaDto? =
        registrationInformationClient.getMercadoria(id)

    fun decreaseQuantidade(
        pedidoId: UUID,
        updateQuantidadeBatchForms: Collection<MercadoriaDecreaseQuantidadeForm>
    ) =
        publisher.publishDecreaseQuantidade(
            Message(
                requestId = pedidoId,
                body = MercadoriaDecreaseQuantidadeMessage(updateQuantidadeBatchForms)
            )
        )
}
