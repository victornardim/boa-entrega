package boa.entrega.registration.information.model.message

import boa.entrega.registration.information.model.domain.MercadoriaTipo
import java.util.UUID

data class MercadoriaMessage(
    val id: UUID,
    val nome: String,
    val fornecedorId: UUID,
    val depositoId: UUID,
    val quantidade: Double,
    val codigoBarras: String,
    val tipo: MercadoriaTipo,
    val dataValidade: String
) {
    fun toEvent() = Message(body = this)
}
