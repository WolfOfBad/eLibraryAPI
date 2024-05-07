package ru.wolfofbad.elibraryapi.service

import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import org.springframework.stereotype.Service
import ru.wolfofbad.elibraryapi.configuration.ApplicationConfiguration
import ru.wolfofbad.elibraryapi.exception.RateLimitExceededException
import ru.wolfofbad.elibraryapi.generated.model.ArticleResponse
import java.io.File
import java.net.URI
import java.util.stream.Collectors
import java.util.stream.Stream

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
        val journalTitle = getJournalTitle(info)
        val number = getNumber(info)

        return ArticleResponse(
            id,
            title,
            author,
            workplace,
            type,
            language,
            year,
            journalTitle,
            number,
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

    private fun getWorkplace(info: Element): List<String> {
        return try {
            info.child(3)
                .child(1)
                .child(0)
                .child(0)
                .child(1)
                .children().stream()
                    .filter{ it.hasClass("help1 pointer") || it.attributes().get("color").equals("#00008f") }
                    .map { it.text() }
                    .collect(Collectors.toList())

        } catch (e: Exception) {
            emptyList()
        }

    }

    private fun getType(info: Element): String? {
        return try {
            info.child(3).children().stream()
                .filter{it.text().lastIndexOf("Тип:") != -1}
                .findFirst().get()
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
            info.child(3).children().stream()
                .filter{it.text().lastIndexOf("Язык:") != -1}
                .findFirst().get()
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
            val first = info.child(3).stream()
                .filter{it.text().lastIndexOf("Год:") != -1 || it.text().lastIndexOf("Год издания:") != -1}
                .findFirst().get()
                .getElementsContainingOwnText("Год")
                .first()
                ?.child(3)
                ?.ownText()

            return first?.toLong()
        } catch (e: Exception) {
            null
        }
    }

    private fun getJournalTitle(info: Element): String? {
        return try {

            info.child(3).children().stream()
                    .filter{it.text().lastIndexOf("ЖУРНАЛ:") != -1}
                    .findFirst().get()
                    .child(0)
                    .child(1)
                    .child(1)
                    .child(1)
                    .ownText()

        } catch (e: Exception) {
            null
        }
    }

    private fun getNumber(info: Element): Long? {
        return try {
            info.child(3).stream()
                    .filter{it.text().lastIndexOf("Номер:") != -1}
                    .findFirst().get()
                    .getElementsContainingOwnText("Номер:")
                    .first()
                    ?.child(1)
                    ?.ownText()
                    ?.toLong()
        } catch (e: Exception) {
            return null
        }
    }
}