package boa.entrega.registration.information.model.dto

import boa.entrega.registration.information.model.domain.Endereco
import boa.entrega.registration.information.model.domain.EnderecoTipo
import java.io.Serializable
import java.util.UUID

data class EnderecoDto(
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
) : Serializable {
    fun toDomain(): Endereco =
        Endereco(
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
