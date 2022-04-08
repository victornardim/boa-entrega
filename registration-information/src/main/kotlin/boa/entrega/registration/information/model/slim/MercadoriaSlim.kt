package boa.entrega.registration.information.model.slim

import boa.entrega.registration.information.model.domain.Deposito
import boa.entrega.registration.information.model.domain.Fornecedor
import boa.entrega.registration.information.model.domain.Mercadoria
import boa.entrega.registration.information.model.domain.MercadoriaTipo
import boa.entrega.registration.information.model.message.MercadoriaMessage
import boa.entrega.registration.information.model.message.Message
import java.util.Date
import java.util.UUID

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

    fun toMessage(): Message<MercadoriaMessage> =
        Message(
            body = MercadoriaMessage(
                id = id,
                nome = nome,
                fornecedorId = fornecedorId,
                depositoId = depositoId,
                quantidade = quantidade,
                codigoBarras = codigoBarras,
                tipo = tipo,
                dataValidade = dataValidade.toString()
            )
        )
}
