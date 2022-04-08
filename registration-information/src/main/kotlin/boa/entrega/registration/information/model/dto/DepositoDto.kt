package boa.entrega.registration.information.model.dto

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
) : Serializable
