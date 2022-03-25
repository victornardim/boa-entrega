package boa.entrega.registration.information.model.slim

import boa.entrega.registration.information.model.domain.Deposito
import boa.entrega.registration.information.model.domain.DepositoCapacidadeTipo
import boa.entrega.registration.information.model.domain.DepositoTipo
import boa.entrega.registration.information.model.domain.Endereco
import java.util.UUID

data class DepositoSlim(
    val id: UUID,
    val enderecoId: UUID,
    val tipo: DepositoTipo,
    val capacidade: Double,
    val capacidadeTipo: DepositoCapacidadeTipo
) {
    fun toComplete(endereco: Endereco): Deposito =
        Deposito(
            id = id,
            endereco = endereco,
            tipo = tipo,
            capacidade = capacidade,
            capacidadeTipo = capacidadeTipo
        )
}