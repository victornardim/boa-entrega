package boa.entrega.orders.model.domain

import boa.entrega.orders.model.dto.PedidoDto
import java.time.Instant
import java.util.UUID

data class Pedido(
    val id: UUID,
    val codigoRastreio: String,
    val cliente: Cliente,
    val andamento: Collection<Andamento>,
    val data: Instant,
    val mercadorias: Collection<Mercadoria>
) {
    fun toDto(): PedidoDto =
        PedidoDto(
            id = id,
            codigoRastreio = codigoRastreio,
            cliente = cliente.toDto(),
            andamento = andamento,
            data = data,
            mercadorias = mercadorias.map { it.toDto() }
        )
}
