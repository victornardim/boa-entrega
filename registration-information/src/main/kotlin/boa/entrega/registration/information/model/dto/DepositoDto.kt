package boa.entrega.registration.information.model.dto

import boa.entrega.registration.information.model.domain.Deposito
import boa.entrega.registration.information.model.domain.DepositoCapacidadeTipo
import boa.entrega.registration.information.model.domain.DepositoTipo
import java.io.Serializable
import java.util.UUID

data class DepositoDto(
    val id: UUID,
    val endereco: EnderecoDto,
    val tipo: DepositoTipo,
    val capacidade: Double,
    val capacidadeTipo: DepositoCapacidadeTipo
): Serializable {
    fun toDomain(): Deposito =
        Deposito(
            id = id,
            endereco = endereco.toDomain(),
            tipo = tipo,
            capacidade = capacidade,
            capacidadeTipo = capacidadeTipo
        )
}