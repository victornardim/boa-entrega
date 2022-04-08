package boa.entrega.registration.information.service

import boa.entrega.registration.information.model.domain.Deposito
import boa.entrega.registration.information.repository.DepositoRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class DepositoService(
    private val repository: DepositoRepository,
    private val enderecoService: EnderecoService
) {
    fun list(offset: Int, limit: Int): Collection<Deposito> =
        repository.list(offset, limit).map { it.toComplete(enderecoService.get(it.enderecoId)) }

    fun get(id: UUID): Deposito =
        repository.get(id).let { it.toComplete(enderecoService.get(it.enderecoId)) }
}
