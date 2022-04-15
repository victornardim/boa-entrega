package boa.entrega.orders.model.message

import boa.entrega.orders.model.form.MercadoriaDecreaseQuantidadeForm

data class MercadoriaDecreaseQuantidadeMessage(
    val forms: Collection<MercadoriaDecreaseQuantidadeForm>
)
