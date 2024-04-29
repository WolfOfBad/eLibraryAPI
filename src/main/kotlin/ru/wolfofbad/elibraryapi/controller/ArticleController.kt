package ru.wolfofbad.elibraryapi.controller

import org.apache.logging.log4j.LogManager
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import ru.wolfofbad.elibraryapi.generated.api.ArticleApi
import ru.wolfofbad.elibraryapi.generated.model.ArticleResponse
import ru.wolfofbad.elibraryapi.service.ArticleService

@RestController
class ArticleController(
    val service: ArticleService
) : ArticleApi {
    private val logger = LogManager.getLogger(ArticleController::class.java)

    override fun getArticleInfo(id: Long): ResponseEntity<ArticleResponse> {
        val body = service.parseArticle(id)
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(body)
    }
}