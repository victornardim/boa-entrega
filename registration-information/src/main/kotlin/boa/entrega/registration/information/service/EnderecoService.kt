package boa.entrega.registration.information.service

import boa.entrega.registration.information.model.domain.Endereco
import boa.entrega.registration.information.repository.EnderecoRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class EnderecoService(
    private val repository: EnderecoRepository
) {
    fun get(id: UUID): Endereco =
        repository.get(id)
}
