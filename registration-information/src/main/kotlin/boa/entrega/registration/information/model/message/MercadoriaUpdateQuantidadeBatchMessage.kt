package boa.entrega.registration.information.model.message

import boa.entrega.registration.information.model.form.MercadoriaUpdateQuantidadeBatchForm
import java.util.UUID

data class MercadoriaUpdateQuantidadeBatchMessage(
    val requisicaoId: UUID,
    val forms: Collection<MercadoriaUpdateQuantidadeBatchForm>
) {
    fun toMessage(): Message<MercadoriaUpdateQuantidadeBatchMessage> =
        Message(
            body = this
        )
}
