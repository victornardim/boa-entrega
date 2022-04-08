package boa.entrega.orders.controller

import boa.entrega.orders.model.domain.AndamentoStatus
import boa.entrega.orders.model.dto.PedidoDto
import boa.entrega.orders.model.form.PedidoCreateForm
import boa.entrega.orders.service.PedidoService
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
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.util.UUID
import javax.validation.Valid

@Tag(
    name = "Mercadoria controller",
    description = "API responsável por manter pedidos"
)
@RestController("Pedido controller")
@RequestMapping("/v1/pedido")
class PedidoController(
    private val service: PedidoService
) {
    @GetMapping("/andamento/{andamento}")
    @Operation(summary = "Devolve todos os pedidos de um status específico")
    @Timed(histogram = true)
    fun listByAndamento(
        @PathVariable("andamento") andamento: AndamentoStatus,
        @RequestParam(required = false, defaultValue = "0") offset: Int,
        @RequestParam(required = false, defaultValue = "50") limit: Int
    ): Collection<PedidoDto> = service.listByAndamento(andamento, offset, limit).map { it.toDto() }

    @GetMapping("/cliente/{clienteId}")
    @Operation(summary = "Devolve todos os pedidos de um cliente específico")
    @Timed(histogram = true)
    fun listByCliente(
        @PathVariable("clienteId") clienteId: UUID,
        @RequestParam(required = false, defaultValue = "0") offset: Int,
        @RequestParam(required = false, defaultValue = "10") limit: Int
    ): Collection<PedidoDto> = service.listByCliente(clienteId, offset, limit).map { it.toDto() }

    @GetMapping("/{id}")
    @Operation(summary = "Devolve um pedido")
    @Timed(histogram = true)
    @Cacheable(key = "{#id}", cacheNames = ["pedido"], cacheManager = "pedidoCacheManager")
    fun get(
        @PathVariable("id") id: UUID
    ): PedidoDto = service.get(id).toDto()

    @GetMapping("/rastreio/{codigoRastreio}")
    @Operation(summary = "Devolve um pedido de acordo com seu código de rastreio")
    @Timed(histogram = true)
    fun getByCodigoRastreio(
        @PathVariable("codigoRastreio") codigoRastreio: String
    ): PedidoDto = service.getByCodigoRastreio(codigoRastreio).toDto()

    @PostMapping()
    @Operation(summary = "Cria um novo pedido")
    @ResponseStatus(HttpStatus.CREATED)
    fun create(
        @RequestBody() @Valid form: PedidoCreateForm
    ): UUID = service.create(form)

    @PatchMapping("/{id}")
    @Operation(summary = "Atualiza o andamento do pedido para o próximo status")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CacheEvict(value = ["pedido"], key = "{#id}")
    fun proceed(
        @PathVariable("id") id: UUID
    ) = service.proceed(id)

    @DeleteMapping("/{id}")
    @Operation(summary = "Cancela um pedido, alterando seu status para CANCELADO")
    @CacheEvict(value = ["pedido"], key = "{#id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun cancel(
        @PathVariable("id") id: UUID
    ) = service.cancel(id)
}
