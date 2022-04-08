package boa.entrega.orders.service

import boa.entrega.orders.client.RegistrationInformationClient
import boa.entrega.orders.model.dto.MercadoriaDto
import boa.entrega.orders.model.form.MercadoriaUpdateQuantidadeBatchForm
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class MercadoriaService(
    private val registrationInformationClient: RegistrationInformationClient
) {
    fun get(id: UUID): MercadoriaDto? =
        registrationInformationClient.getMercadoria(id)

    fun updateQuantidade(updateQuantidadeBatchForms: Collection<MercadoriaUpdateQuantidadeBatchForm>): UUID =
        registrationInformationClient.updateMercadoriaQuantidade(updateQuantidadeBatchForms)
}
