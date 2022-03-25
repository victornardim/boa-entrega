package boa.entrega.registration.information.model.dto

import boa.entrega.registration.information.model.domain.Fornecedor
import java.io.Serializable
import java.util.UUID

data class FornecedorDto(
    val id: UUID,
    val razaoSocial: String,
    val nomeFantasia: String,
    val cnpj: String,
    val endereco: EnderecoDto
): Serializable {
    fun toDomain(): Fornecedor =
        Fornecedor(
            id = id,
            razaoSocial = razaoSocial,
            nomeFantasia = nomeFantasia,
            cnpj = cnpj,
            endereco = endereco.toDomain()
        )
}