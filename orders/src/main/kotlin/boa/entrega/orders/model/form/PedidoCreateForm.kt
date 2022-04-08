package boa.entrega.orders.model.form

import boa.entrega.orders.model.slim.PedidoSlim
import java.time.Instant
import java.util.UUID
import javax.validation.constraints.NotEmpty

data class PedidoCreateForm(
    @NotEmpty
    val clienteId: UUID,

    @NotEmpty
    val mercadorias: Collection<MercadoriaPedidoCreateForm>
) {
    fun toSlim(id: UUID): PedidoSlim =
        PedidoSlim(
            id = id,
            codigoRastreio = Instant.now().toEpochMilli().toString(),
            clienteId = clienteId,
            data = Instant.now()
        )
}
