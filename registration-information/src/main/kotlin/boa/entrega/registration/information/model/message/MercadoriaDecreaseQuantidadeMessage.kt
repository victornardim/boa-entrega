package boa.entrega.registration.information.model.message

import boa.entrega.registration.information.model.form.MercadoriaDecreaseQuantidadeForm

data class MercadoriaDecreaseQuantidadeMessage(
    val forms: Collection<MercadoriaDecreaseQuantidadeForm>
)
