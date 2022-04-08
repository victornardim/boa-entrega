package boa.entrega.registration.information.controller

import boa.entrega.registration.information.facade.MercadoriaBatchFacade
import boa.entrega.registration.information.model.form.MercadoriaUpdateQuantidadeBatchForm
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.util.UUID
import javax.validation.Valid

@Tag(
    name = "Mercadoria batch controller [Async]",
    description = "API assíncrona, responsável por realizar alterações em lote nas mercadorias"
)
@RestController
@RequestMapping("/v1/mercadoria/batch")
class MercadoriaBatchController(
    private val facade: MercadoriaBatchFacade
) {
    @PatchMapping()
    @Operation(summary = "Remove da quantidade de uma mercadoria")
    @ResponseStatus(HttpStatus.CREATED)
    fun decreaseQuantidade(
        @RequestBody() @Valid mercadoriaUpdateQuantidadeForms: Collection<MercadoriaUpdateQuantidadeBatchForm>
    ): UUID = facade.createDecreaseQuantidadeRequest(mercadoriaUpdateQuantidadeForms)
}
