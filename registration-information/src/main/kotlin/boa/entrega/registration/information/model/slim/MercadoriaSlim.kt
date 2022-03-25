package boa.entrega.registration.information.model.slim

import boa.entrega.registration.information.model.domain.*
import java.util.UUID
import java.util.Date

data class MercadoriaSlim(
    val id: UUID,
    val nome: String,
    val fornecedorId: UUID,
    val depositoId: UUID,
    val quantidade: Double,
    val codigoBarras: String,
    val tipo: MercadoriaTipo,
    val dataValidade: Date
) {
    fun toComplete(fornecedor: Fornecedor, deposito: Deposito): Mercadoria =
        Mercadoria(
            id = id,
            nome = nome,
            fornecedor = fornecedor,
            deposito = deposito,
            quantidade = quantidade,
            codigoBarras = codigoBarras,
            tipo = tipo,
            dataValidade = dataValidade
        )

    fun toEvent() = Event(body = this)
}