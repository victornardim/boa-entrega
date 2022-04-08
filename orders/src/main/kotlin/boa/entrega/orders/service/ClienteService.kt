package boa.entrega.orders.service

import boa.entrega.orders.client.RegistrationInformationClient
import boa.entrega.orders.model.dto.ClienteDto
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class ClienteService(
    private val registrationInformationClient: RegistrationInformationClient
) {
    fun get(id: UUID): ClienteDto? =
        registrationInformationClient.getCliente(id)
}
