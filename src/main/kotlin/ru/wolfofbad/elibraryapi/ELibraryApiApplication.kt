package ru.wolfofbad.elibraryapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import ru.wolfofbad.elibraryapi.configuration.ApplicationConfiguration
import ru.wolfofbad.elibraryapi.configuration.GoogleSheetsConfiguration

@SpringBootApplication
@EnableConfigurationProperties(ApplicationConfiguration::class, GoogleSheetsConfiguration::class)
class ELibraryApiApplication

fun main(args: Array<String>) {
    runApplication<ELibraryApiApplication>(*args)
}
