package boa.entrega.orders.model.dto

import boa.entrega.orders.model.domain.Mercadoria
import boa.entrega.orders.model.domain.MercadoriaTipo
import java.io.Serializable
import java.util.Date
import java.util.UUID

data class MercadoriaDto(
    val id: UUID,
    val nome: String,
    val quantidade: Double,
    val codigoBarras: String,
    val tipo: MercadoriaTipo,
    val dataValidade: Date
) : Serializable {
    fun toDomain(): Mercadoria =
        Mercadoria(
            id = id,
            nome = nome,
            quantidade = quantidade,
            codigoBarras = codigoBarras,
            tipo = tipo,
            dataValidade = dataValidade
        )
}
