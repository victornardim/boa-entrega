package boa.entrega.registration.information.model.domain

import boa.entrega.registration.information.model.dto.ClienteDto
import java.util.UUID

data class Cliente(
    val id: UUID,
    val endereco: Endereco,
    val nome: String,
    val cpf: String,
    val telefone: String,
    val email: String
) {
    fun toDto(): ClienteDto =
        ClienteDto(
            id = id,
            endereco = endereco.toDto(),
            nome = nome,
            cpf = cpf,
            telefone = telefone,
            email = email
        )
}