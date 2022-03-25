package boa.entrega.registration.information.controller

import boa.entrega.registration.information.service.ClienteService
import boa.entrega.registration.information.model.dto.ClienteDto
import io.micrometer.core.annotation.Timed
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.cache.annotation.Cacheable
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.PathVariable
import java.util.UUID

@Tag(
    name = "Cliente controller",
    description = "API responsável por manter clientes"
)
@RestController("Cliente controller")
@RequestMapping("/v1/cliente")
class ClienteController(
    private val service: ClienteService
) {
    @GetMapping()
    @Operation(summary = "Devolve todos os clientes")
    @Timed(histogram = true)
    fun list(
        @RequestParam(required = false, defaultValue = "0") offset: Int,
        @RequestParam(required = false, defaultValue = "50") limit: Int
    ): Collection<ClienteDto> =
        service.list(offset, limit).map { it.toDto() }

    @GetMapping("/{id}")
    @Operation(summary = "Devolve um cliente")
    @Timed(histogram = true)
    @Cacheable(key = "{#id}", cacheNames = ["cliente"], cacheManager = "clienteCacheManager")
    fun get(
        @PathVariable("id") id: UUID
    ): ClienteDto =
        service.get(id).toDto()
}