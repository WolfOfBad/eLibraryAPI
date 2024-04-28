package ru.wolfofbad.elibraryapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import ru.wolfofbad.elibraryapi.configuration.ApplicationConfiguration

@SpringBootApplication
@EnableConfigurationProperties(ApplicationConfiguration::class)
class ELibraryApiApplication

fun main(args: Array<String>) {
    runApplication<ELibraryApiApplication>(*args)
}
