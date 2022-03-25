package boa.entrega.registration.information.model.dto

import boa.entrega.registration.information.model.domain.Mercadoria
import boa.entrega.registration.information.model.domain.MercadoriaTipo
import java.io.Serializable
import java.time.Instant
import java.util.UUID
import java.util.Date

data class MercadoriaDto(
    val id: UUID,
    val nome: String,
    val fornecedor: FornecedorDto,
    val deposito: DepositoDto,
    val quantidade: Double,
    val codigoBarras: String,
    val tipo: MercadoriaTipo,
    val dataValidade: Date
): Serializable {
    fun toDomain(): Mercadoria =
        Mercadoria(
            id = id,
            nome = nome,
            fornecedor = fornecedor.toDomain(),
            deposito = deposito.toDomain(),
            quantidade = quantidade,
            codigoBarras = codigoBarras,
            tipo = tipo,
            dataValidade = dataValidade
        )
}