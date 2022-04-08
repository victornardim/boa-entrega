package boa.entrega.orders.model.form

import boa.entrega.orders.model.slim.MercadoriaPedidoSlim
import java.util.UUID
import javax.validation.constraints.DecimalMin
import javax.validation.constraints.NotEmpty

data class MercadoriaPedidoCreateForm(
    @NotEmpty
    val id: UUID,

    @NotEmpty
    @DecimalMin("0.0")
    val quantidade: Double
) {
    fun toSlim(pedidoId: UUID): MercadoriaPedidoSlim =
        MercadoriaPedidoSlim(
            pedidoId = pedidoId,
            mercadoriaId = id,
            quantidade = quantidade
        )
}
