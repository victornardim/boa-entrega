package boa.entrega.registration.information.service

import boa.entrega.registration.information.model.domain.Cliente
import boa.entrega.registration.information.repository.ClienteRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class ClienteService(
    private val repository: ClienteRepository,
    private val enderecoService: EnderecoService
) {
    fun list(offset: Int, limit: Int): Collection<Cliente> =
        repository.list(offset, limit).map {
            it.toComplete(enderecoService.get(it.enderecoId))
        }

    fun get(id: UUID): Cliente {
        return repository.get(id).let { it.toComplete(enderecoService.get(it.enderecoId)) }
    }
}
