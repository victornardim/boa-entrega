package boa.entrega.registration.information.controller

import boa.entrega.registration.information.model.dto.DepositoDto
import boa.entrega.registration.information.service.DepositoService
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
    name = "Deposito controller",
    description = "API responsável por manter depósitos"
)
@RestController("Deposito controller")
@RequestMapping("/v1/deposito")
class DepositoController(
    private val service: DepositoService
) {
    @GetMapping()
    @Operation(summary = "Devolve todos depósitos")
    @Timed(histogram = true)
    fun list(
        @RequestParam(required = false, defaultValue = "0") offset: Int,
        @RequestParam(required = false, defaultValue = "50") limit: Int
    ): Collection<DepositoDto> =
        service.list(offset, limit).map { it.toDto() }

    @GetMapping("/{id}")
    @Operation(summary = "Devolve um depósito")
    @Timed(histogram = true)
    @Cacheable(key = "{#id}", cacheNames = ["deposito"], cacheManager = "depositoCacheManager")
    fun get(
        @PathVariable("id") id: UUID
    ): DepositoDto =
        service.get(id).toDto()
}