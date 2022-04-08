package boa.entrega.orders.model.message

import boa.entrega.orders.model.domain.Andamento
import boa.entrega.orders.model.slim.MercadoriaPedidoSlim
import java.util.UUID

data class PedidoMessage(
    val id: UUID,
    val codigoRastreio: String,
    val clienteId: UUID,
    val ultimoAndamento: Andamento,
    val data: String,
    val mercadorias: Collection<MercadoriaPedidoSlim>
)
