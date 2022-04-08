package boa.entrega.registration.information.model.domain

import boa.entrega.registration.information.model.dto.DepositoDto
import java.util.UUID

data class Deposito(
    val id: UUID,
    val endereco: Endereco,
    val tipo: DepositoTipo,
    val capacidade: Double,
    val capacidadeTipo: DepositoCapacidadeTipo
) {
    fun toDto(): DepositoDto =
        DepositoDto(
            id = id,
            endereco = endereco.toDto(),
            tipo = tipo,
            capacidade = capacidade,
            capacidadeTipo = capacidadeTipo
        )
}
