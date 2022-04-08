package boa.entrega.registration.information.model.domain

import boa.entrega.registration.information.model.dto.FornecedorDto
import java.util.UUID

data class Fornecedor(
    val id: UUID,
    val razaoSocial: String,
    val nomeFantasia: String,
    val cnpj: String,
    val endereco: Endereco
) {
    fun toDto(): FornecedorDto =
        FornecedorDto(
            id = id,
            razaoSocial = razaoSocial,
            nomeFantasia = nomeFantasia,
            cnpj = cnpj,
            endereco = endereco.toDto()
        )
}
