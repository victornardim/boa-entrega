package boa.entrega.registration.information.service

import boa.entrega.registration.information.model.domain.Fornecedor
import boa.entrega.registration.information.repository.FornecedorRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class FornecedorService(
    private val repository: FornecedorRepository,
    private val enderecoService: EnderecoService
) {
    fun list(offset: Int, limit: Int): Collection<Fornecedor> =
        repository.list(offset, limit).map { it.toComplete(enderecoService.get(it.enderecoId)) }

    fun get(id: UUID): Fornecedor =
        repository.get(id).let { it.toComplete(enderecoService.get(it.enderecoId)) }
}
