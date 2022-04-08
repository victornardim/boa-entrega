package boa.entrega.orders.model.domain

import boa.entrega.orders.model.dto.EnderecoDto
import java.util.UUID

data class Endereco(
    val id: UUID,
    val tipo: EnderecoTipo,
    val logradouro: String,
    val numero: String,
    val complemento: String?,
    val bairro: String,
    val cidade: String,
    val uf: String,
    val cep: String,
    val latitude: Double,
    val longitude: Double
) {
    fun toDto(): EnderecoDto =
        EnderecoDto(
            id = id,
            tipo = tipo,
            logradouro = logradouro,
            numero = numero,
            complemento = complemento,
            bairro = bairro,
            cidade = cidade,
            uf = uf,
            cep = cep,
            latitude = latitude,
            longitude = longitude
        )
}
