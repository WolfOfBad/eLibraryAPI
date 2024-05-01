package ru.wolfofbad.elibraryapi.generated.api

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import ru.wolfofbad.elibraryapi.generated.model.AddArticleRequest
import ru.wolfofbad.elibraryapi.generated.model.ApiErrorResponse

@Validated
@Tag(name = "sheets", description = "The google sheets API")
interface SheetsApi {
    @Operation(
        summary = "Добавить запись в google sheets",
        operationId = "postArticleInfo",
        description = """""",
        responses = [
            ApiResponse(responseCode = "200", description = "Данные успешно сохранены"),
            ApiResponse(
                responseCode = "400",
                description = "Некорректные параметры запроса",
                content = [Content(schema = Schema(implementation = ApiErrorResponse::class))]
            )
        ]
    )
    @RequestMapping(
        method = [RequestMethod.POST],
        value = ["/sheets/append"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun postArticleInfo(
        @Parameter(
            description = "",
            required = true
        ) @Valid @RequestBody addArticleRequest: AddArticleRequest
    ): ResponseEntity<Unit> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }
}