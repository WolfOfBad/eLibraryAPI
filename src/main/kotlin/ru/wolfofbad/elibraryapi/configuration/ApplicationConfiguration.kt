package ru.wolfofbad.elibraryapi.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.bind.DefaultValue
import org.springframework.boot.context.properties.bind.Name

@ConfigurationProperties(prefix = "app", ignoreUnknownFields = false)
data class ApplicationConfiguration(
    @Name("eLibrary-url")
    @DefaultValue("https://elibrary.ru")
    val eLibraryUrl: String
) {
}