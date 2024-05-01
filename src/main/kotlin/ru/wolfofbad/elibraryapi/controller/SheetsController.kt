package ru.wolfofbad.elibraryapi.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import ru.wolfofbad.elibraryapi.generated.api.SheetsApi
import ru.wolfofbad.elibraryapi.generated.model.AddArticleRequest
import ru.wolfofbad.elibraryapi.service.SheetsService

@RestController
class SheetsController(
    private val service: SheetsService
) : SheetsApi {
    override fun postArticleInfo(addArticleRequest: AddArticleRequest): ResponseEntity<Unit> {
        service.appendData(addArticleRequest)
        return ResponseEntity.ok().build()
    }
}