package ru.wolfofbad.elibraryapi.controller

import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import ru.wolfofbad.elibraryapi.exception.RateLimitExceededException
import ru.wolfofbad.elibraryapi.generated.model.ApiErrorResponse
import ru.wolfofbad.elibraryapi.generated.model.RateLimitErrorResponse

@RestControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE)
class ExceptionApiHandler {
    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): ResponseEntity<ApiErrorResponse> {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(
                ApiErrorResponse(
                    "Some exception occurred during the request",
                    HttpStatus.BAD_REQUEST.toString(),
                    e::class.toString(),
                    e.message,
                    e.stackTrace
                        .map { it.toString() }
                        .toList()
                )
            )
    }

    @ExceptionHandler(RateLimitExceededException::class)
    fun handleException(e: RateLimitExceededException): ResponseEntity<RateLimitErrorResponse> {
        return ResponseEntity
            .status(HttpStatus.TOO_MANY_REQUESTS)
            .body(
                RateLimitErrorResponse(
                    "Rate limit exceeded on eLibrary website",
                    "To continue sending requests, complete captcha on https://www.elibrary.ru/ or implement " +
                            "some dynamic proxy and readdress requests from this service"
                )
            )

    }
}