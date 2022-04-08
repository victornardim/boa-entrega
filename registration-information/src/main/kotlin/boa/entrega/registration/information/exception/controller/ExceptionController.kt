package boa.entrega.registration.information.exception.controller

import boa.entrega.registration.information.exception.dto.BadRequestException
import boa.entrega.registration.information.exception.dto.ResponseError
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

    @ExceptionHandler(BadRequestException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleBadRequestException(badRequestException: BadRequestException) =
        badRequestException.message?.let { ResponseError(it) }
}
