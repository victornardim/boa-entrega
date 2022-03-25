package boa.entrega.registration.information.model.slim

import boa.entrega.registration.information.model.domain.Endereco
import boa.entrega.registration.information.model.domain.Fornecedor
import java.util.UUID

data class FornecedorSlim (
    val id: UUID,
    val razaoSocial: String,
    val nomeFantasia: String,
    val cnpj: String,
    val enderecoId: UUID
) {
    fun toComplete(endereco: Endereco): Fornecedor =
        Fornecedor(
            id = id,
            razaoSocial = razaoSocial,
            nomeFantasia = nomeFantasia,
            cnpj = cnpj,
            endereco = endereco
        )
}
