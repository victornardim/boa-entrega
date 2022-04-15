package boa.entrega.orders.client

import boa.entrega.orders.model.dto.ClienteDto
import boa.entrega.orders.model.dto.MercadoriaDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import java.util.UUID

@FeignClient(name = "registration-information-client", url = "\${clients.registration-information.url}")
interface RegistrationInformationClient {
    @GetMapping(path = ["/v1/mercadoria/{id}"])
    fun getMercadoria(@PathVariable("id") id: UUID): MercadoriaDto?

    @GetMapping(path = ["/v1/cliente/{id}"])
    fun getCliente(@PathVariable("id") id: UUID): ClienteDto?
}
