package boa.entrega.registration.information.model.dto

import boa.entrega.registration.information.model.domain.MercadoriaTipo
import java.io.Serializable
import java.util.Date
import java.util.UUID

data class MercadoriaDto(
    val id: UUID,
    val nome: String,
    val fornecedor: FornecedorDto,
    val deposito: DepositoDto,
    val quantidade: Double,
    val codigoBarras: String,
    val tipo: MercadoriaTipo,
    val dataValidade: Date
) : Serializable
