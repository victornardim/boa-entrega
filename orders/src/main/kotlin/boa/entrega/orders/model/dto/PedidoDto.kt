package boa.entrega.orders.model.dto

import boa.entrega.orders.model.domain.Andamento
import java.io.Serializable
import java.time.Instant
import java.util.UUID

data class PedidoDto(
    val id: UUID,
    val codigoRastreio: String,
    val cliente: ClienteDto,
    val andamento: Collection<Andamento>,
    val data: Instant,
    val mercadorias: Collection<MercadoriaDto>
) : Serializable
