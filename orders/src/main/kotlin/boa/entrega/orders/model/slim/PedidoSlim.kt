package boa.entrega.orders.model.slim

import boa.entrega.orders.model.domain.Andamento
import boa.entrega.orders.model.domain.Cliente
import boa.entrega.orders.model.domain.Mercadoria
import boa.entrega.orders.model.domain.Pedido
import boa.entrega.orders.model.message.Message
import boa.entrega.orders.model.message.PedidoMessage
import java.time.Instant
import java.util.UUID

data class PedidoSlim(
    val id: UUID,
    val codigoRastreio: String,
    val clienteId: UUID,
    val data: Instant
) {
    fun toComplete(
        cliente: Cliente,
        andamento: Collection<Andamento>,
        mercadorias: Collection<Mercadoria>
    ): Pedido =
        Pedido(
            id = id,
            codigoRastreio = codigoRastreio,
            cliente = cliente,
            andamento = andamento,
            mercadorias = mercadorias,
            data = data
        )

    fun toMessage(
        ultimoAndamento: Andamento,
        mercadorias: Collection<MercadoriaPedidoSlim>
    ): Message<PedidoMessage> =
        Message(
            body = PedidoMessage(
                id = id,
                codigoRastreio = codigoRastreio,
                clienteId = clienteId,
                ultimoAndamento = ultimoAndamento,
                mercadorias = mercadorias,
                data = data.toString(),
            )
        )
}
