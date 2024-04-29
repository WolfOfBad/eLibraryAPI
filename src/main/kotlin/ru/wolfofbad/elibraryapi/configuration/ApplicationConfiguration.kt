package ru.wolfofbad.elibraryapi.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.bind.DefaultValue
import org.springframework.boot.context.properties.bind.Name
import java.net.URI

@ConfigurationProperties(prefix = "app", ignoreUnknownFields = false)
data class ApplicationConfiguration(
    @Name("eLibrary-url")
    @DefaultValue("https://www.elibrary.ru")
    val eLibraryUrl: URI
)