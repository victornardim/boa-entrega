package boa.entrega.registration.information.model.dto

import java.io.Serializable
import java.util.UUID

data class ClienteDto(
    val id: UUID,
    val endereco: EnderecoDto,
    val nome: String,
    val cpf: String,
    val telefone: String,
    val email: String
) : Serializable
