package ru.wolfofbad.elibraryapi.service

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.extensions.wiremock.ListenerMode
import io.kotest.extensions.wiremock.WireMockListener
import io.kotest.matchers.shouldBe
import ru.wolfofbad.elibraryapi.configuration.ApplicationConfiguration
import ru.wolfofbad.elibraryapi.exception.RateLimitExceededException
import ru.wolfofbad.elibraryapi.generated.model.ArticleResponse
import java.io.File
import java.net.URI
import java.nio.file.Files

class ArticleServiceTest : FunSpec({
    val wiremockServer = WireMockServer(9000)
    listener(WireMockListener(wiremockServer, ListenerMode.PER_SPEC))

    test("Parsing test 1") {
        val file = File("./src/test/resources/html/1.html")
        wiremockServer.stubFor(
            WireMock.get(WireMock.urlEqualTo("/item.asp?id=1"))
                .willReturn(
                    WireMock.aResponse()
                        .withStatus(200)
                        .withBody(Files.readAllLines(file.toPath()).joinToString("\n"))
                )
        )

        val service = ArticleService(
            ApplicationConfiguration(
                URI.create(wiremockServer.baseUrl())
            )
        )

        val result = service.parseArticle(1)

        result shouldBe ArticleResponse(
            1,
            "ANALYSIS OF CURRENT STATE, DEVELOPMENT PROSPECTS AND OPTIMIZATION OF PASSENGER TRAFFIC IN THE CITY OF IZHEVSK",
            listOf("KASATKINA E.", "KETOVA K.", "VAVILOVA D."),
            "Kalashnikov Izhevsk State Technical University",
            "статья в сборнике трудов конференции",
            "английский",
            2022,
            URI.create("http://localhost:9000/item.asp?id=1")
        )
    }

    test("Parsing test 2") {
        val file = File("./src/test/resources/html/2.html")
        wiremockServer.stubFor(
            WireMock.get(WireMock.urlEqualTo("/item.asp?id=1"))
                .willReturn(
                    WireMock.aResponse()
                        .withStatus(200)
                        .withBody(Files.readAllLines(file.toPath()).joinToString("\n"))
                )
        )

        val service = ArticleService(
            ApplicationConfiguration(
                URI.create(wiremockServer.baseUrl())
            )
        )

        val result = service.parseArticle(1)

        result shouldBe ArticleResponse(
            1,
            "РЕАЛИЗАЦИЯ ПРАВА НА ОСПАРИВАНИЕ СДЕЛКИ И ПРАВА НА ОТКАЗ ОТ ДОГОВОРА В СОЛИДАРНЫХ ОБЯЗАТЕЛЬСТВАХ",
            listOf("РОМАНОВА О.И."),
            "«S&K Вертикаль»",
            "статья в журнале - научная статья",
            "русский",
            2023,
            URI.create("http://localhost:9000/item.asp?id=1")
        )
    }

    test("Parsing test 3") {
        val file = File("./src/test/resources/html/3.html")
        wiremockServer.stubFor(
            WireMock.get(WireMock.urlEqualTo("/item.asp?id=1"))
                .willReturn(
                    WireMock.aResponse()
                        .withStatus(200)
                        .withBody(Files.readAllLines(file.toPath()).joinToString("\n"))
                )
        )

        val service = ArticleService(
            ApplicationConfiguration(
                URI.create(wiremockServer.baseUrl())
            )
        )

        val result = service.parseArticle(1)

        result shouldBe ArticleResponse(
            1,
            "О ПРЕДСТАВЛЕНИИ КВАДРАТУР ОДНОГО КВАЗИДИФФЕРЕНЦИАЛЬНОГО УРАВНЕНИЯ В ВИДЕ СУММ РЯДОВ",
            listOf("ВАТОЛКИН М.Ю."),
            "Ижевский государственный технический университет им. М.Т. Калашникова",
            "статья в сборнике трудов конференции",
            "русский",
            2021,
            URI.create("http://localhost:9000/item.asp?id=1")
        )
    }

    test("Rate limit fail test") {
        val file = File("./src/test/resources/html/error.html")
        wiremockServer.stubFor(
            WireMock.get(WireMock.urlEqualTo("/item.asp?id=1"))
                .willReturn(
                    WireMock.aResponse()
                        .withStatus(200)
                        .withBody(Files.readAllLines(file.toPath()).joinToString("\n"))
                )
        )

        val service = ArticleService(
            ApplicationConfiguration(
                URI.create(wiremockServer.baseUrl())
            )
        )

        shouldThrow<RateLimitExceededException> {
            service.parseArticle(1)
        }
    }

})

