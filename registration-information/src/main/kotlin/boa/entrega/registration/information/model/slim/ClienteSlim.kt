package boa.entrega.registration.information.model.slim

import boa.entrega.registration.information.model.domain.Cliente
import boa.entrega.registration.information.model.domain.Endereco
import java.util.UUID

data class ClienteSlim (
    val id: UUID,
    val enderecoId: UUID,
    val name: String,
    val cpf: String,
    val telefone: String,
    val email: String
) {
    fun toComplete(endereco: Endereco): Cliente =
        Cliente(
            id = id,
            endereco = endereco,
            nome = name,
            cpf = cpf,
            telefone = telefone,
            email = email
        )
}