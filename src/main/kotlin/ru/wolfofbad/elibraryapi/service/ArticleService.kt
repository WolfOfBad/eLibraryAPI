package ru.wolfofbad.elibraryapi.service

import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import org.springframework.stereotype.Service
import ru.wolfofbad.elibraryapi.configuration.ApplicationConfiguration
import ru.wolfofbad.elibraryapi.exception.RateLimitExceededException
import ru.wolfofbad.elibraryapi.generated.model.ArticleResponse
import java.io.File
import java.net.URI

@Service
class ArticleService(
    config: ApplicationConfiguration
) {
    private val baseUrl = config.eLibraryUrl

    fun parseArticle(id: Long): ArticleResponse {
        val url = URI.create("$baseUrl/item.asp?id=$id")
        val document = Jsoup.connect("$baseUrl/item.asp?id=$id").get()

        // check if rate limit exceeded
        if (document.title() == "Тест Тьюринга") {
            throw RateLimitExceededException()
        }

        val info = document
            .getElementsContainingOwnText("ИНФОРМАЦИЯ О ПУБЛИКАЦИИ")
            .parents()[9]
            .lastElementChild()!!
            .firstElementChild()!!

        val title = getTitle(info)
        val author = getAuthors(info)
        val workplace = getWorkplace(info)
        val type = getType(info)
        val language = getLanguage(info)
        val year = getYear(info)

        return ArticleResponse(
            id,
            title,
            author,
            workplace,
            type,
            language,
            year,
            url
        )
    }

    private fun getTitle(info: Element): String? {
        return try {
            info.child(2)
                .child(0)
                .child(0)
                .child(1)
                .child(0)
                .child(0)
                .child(0)
                .ownText()
        } catch (e: Exception) {
            null
        }
    }

    private fun getAuthors(info: Element): List<String> {
        return try {
            info.child(3)
                .child(1)
                .child(0)
                .child(0)
                .child(1)
                .select("div")
                .select("b")
                .map {
                    it.child(0)
                        .ownText()
                }
        } catch (e: Exception) {
            emptyList()
        }
    }

    private fun getWorkplace(info: Element): String? {
        return try {
            val element = info.child(3)
                .child(1)
                .child(0)
                .child(0)
                .child(1)

            return element.child(element.childrenSize() - 2)
                .select("font")
                .first()
                ?.ownText()
        } catch (e: Exception) {
            null
        }

    }

    private fun getType(info: Element): String? {
        return try {
            info.child(3)
                .child(3)
                .getElementsContainingOwnText("Тип")
                .first()
                ?.child(0)
                ?.ownText()
        } catch (e: Exception) {
            null
        }
    }

    private fun getLanguage(info: Element): String? {
        return try {
            info.child(3)
                .child(3)
                .getElementsContainingOwnText("Язык")
                .first()
                ?.child(2)
                ?.ownText()
        } catch (e: Exception) {
            null
        }
    }

    private fun getYear(info: Element): Long? {
        return try {
            // try to find "Год:"
            val first = info.child(3)
                .child(3)
                .getElementsContainingOwnText("Год:")
                .first()
                ?.child(3)
                ?.ownText()

            // try to find "Год издания:"
            val second = info.child(3)
                .child(3)
                .getElementsContainingOwnText("Год издания:")
                .first()
                ?.child(4)
                ?.ownText()

            return first?.toLong() ?: second?.toLong()
        } catch (e: Exception) {
            null
        }


    }
}