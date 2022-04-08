package boa.entrega.orders.exception.controller

import boa.entrega.orders.exception.dto.BadRequestException
import boa.entrega.orders.exception.dto.EntityNotFoundException
import boa.entrega.orders.exception.dto.ResponseError
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionController {
    @ExceptionHandler(NoSuchElementException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleNoSuchElementException(noSuchElementException: NoSuchElementException) =
        ResponseError("Element not found")

    @ExceptionHandler(EntityNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleEntityNotFoundException(entityNotFoundException: EntityNotFoundException) =
        entityNotFoundException.message?.let { ResponseError(it) }

    @ExceptionHandler(BadRequestException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleBadRequestException(badRequestException: BadRequestException) =
        badRequestException.message?.let { ResponseError(it) }
}
