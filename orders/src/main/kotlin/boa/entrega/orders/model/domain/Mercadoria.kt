package boa.entrega.orders.model.domain

import boa.entrega.orders.model.dto.MercadoriaDto
import java.util.Date
import java.util.UUID

data class Mercadoria(
    val id: UUID,
    val nome: String,
    val quantidade: Double,
    val codigoBarras: String,
    val tipo: MercadoriaTipo,
    val dataValidade: Date
) {
    fun toDto(): MercadoriaDto =
        MercadoriaDto(
            id = id,
            nome = nome,
            quantidade = quantidade,
            codigoBarras = codigoBarras,
            tipo = tipo,
            dataValidade = dataValidade
        )
}
