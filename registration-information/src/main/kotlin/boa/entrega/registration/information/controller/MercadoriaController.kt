package boa.entrega.registration.information.controller

import boa.entrega.registration.information.model.dto.MercadoriaDto
import boa.entrega.registration.information.model.form.MercadoriaCreateForm
import boa.entrega.registration.information.model.form.MercadoriaIncreaseQuantidadeForm
import boa.entrega.registration.information.service.MercadoriaService
import io.micrometer.core.annotation.Timed
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.util.UUID
import javax.validation.Valid

@Tag(
    name = "Mercadoria controller",
    description = "API responsável por manter as mercadorias"
)
@RestController("Mercadoria controller")
@RequestMapping("/v1/mercadoria")
class MercadoriaController(
    private val service: MercadoriaService
) {
    @GetMapping("/fornecedor/{fornecedorId}")
    @Operation(summary = "Devolve todas as mercadorias de um fornecedor específico")
    @Timed(histogram = true)
    fun listByFornecedor(
        @PathVariable("fornecedorId") fornecedorId: UUID,
        @RequestParam(required = false, defaultValue = "0") offset: Int,
        @RequestParam(required = false, defaultValue = "50") limit: Int
    ): Collection<MercadoriaDto> = service.listByFornecedor(fornecedorId, offset, limit).map { it.toDto() }

    @GetMapping("/deposito/{depositoId}")
    @Operation(summary = "Devolve todas as mercadorias de um depósito específico")
    @Timed(histogram = true)
    fun listByDeposito(
        @PathVariable("depositoId") depositoId: UUID,
        @RequestParam(required = false, defaultValue = "0") offset: Int,
        @RequestParam(required = false, defaultValue = "50") limit: Int
    ): Collection<MercadoriaDto> = service.listByDeposito(depositoId, offset, limit).map { it.toDto() }

    @GetMapping("/{id}")
    @Operation(summary = "Devolve uma mercadoria")
    @Timed(histogram = true)
    @Cacheable(key = "{#id}", cacheNames = ["mercadoria"], cacheManager = "mercadoriaCacheManager")
    fun get(
        @PathVariable("id") id: UUID
    ): MercadoriaDto = service.get(id).toDto()

    @PostMapping()
    @Operation(summary = "Cria uma nova mercadoria")
    @ResponseStatus(HttpStatus.CREATED)
    fun create(
        @RequestBody() @Valid form: MercadoriaCreateForm
    ) = service.create(form)

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza todos os dados de uma mercadoria")
    @CacheEvict(value = ["mercadoria"], key = "{#id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun update(
        @PathVariable("id") id: UUID,
        @RequestBody() @Valid form: MercadoriaCreateForm
    ) = service.update(id, form)

    @PatchMapping("/{id}")
    @Operation(summary = "Adiciona à quantidadede uma mercadoria")
    @CacheEvict(value = ["mercadoria"], key = "{#id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun increaseQuantidade(
        @PathVariable("id") id: UUID,
        @RequestBody() @Valid form: MercadoriaIncreaseQuantidadeForm
    ) = service.increaseQuantidade(id, form.quantidade)

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete uma mercadoria")
    @CacheEvict(value = ["mercadoria"], key = "{#id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(
        @PathVariable("id") id: UUID
    ) = service.delete(id)
}
