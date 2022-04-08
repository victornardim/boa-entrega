package boa.entrega.registration.information.model.domain

import boa.entrega.registration.information.model.dto.MercadoriaDto
import java.util.Date
import java.util.UUID

data class Mercadoria(
    val id: UUID,
    val nome: String,
    val fornecedor: Fornecedor,
    val deposito: Deposito,
    val quantidade: Double,
    val codigoBarras: String,
    val tipo: MercadoriaTipo,
    val dataValidade: Date
) {
    fun toDto(): MercadoriaDto =
        MercadoriaDto(
            id = id,
            nome = nome,
            fornecedor = fornecedor.toDto(),
            deposito = deposito.toDto(),
            quantidade = quantidade,
            codigoBarras = codigoBarras,
            tipo = tipo,
            dataValidade = dataValidade
        )
}
