package boa.entrega.orders.model.dto

import boa.entrega.orders.model.domain.Cliente
import java.io.Serializable
import java.util.UUID

data class ClienteDto(
    val id: UUID,
    val endereco: EnderecoDto,
    val nome: String,
    val cpf: String,
    val telefone: String,
    val email: String
) : Serializable {
    fun toDomain(): Cliente =
        Cliente(
            id = id,
            endereco = endereco.toDomain(),
            nome = nome,
            cpf = cpf,
            telefone = telefone,
            email = email
        )
}
