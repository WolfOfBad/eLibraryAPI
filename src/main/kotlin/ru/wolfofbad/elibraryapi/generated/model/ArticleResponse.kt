package ru.wolfofbad.elibraryapi.generated.model

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.Valid

/**
 *
 * @param eLibraryId
 * @param title
 * @param authors
 * @param workPlace
 * @param articleType
 * @param language
 * @param year
 * @param journalTitle
 * @param number
 * @param url
 */
data class ArticleResponse(
    @Schema(example = "0", description = "")
    @get:JsonProperty("eLibraryId") val eLibraryId: Long? = null,

    @Schema(example = "null", description = "")
    @get:JsonProperty("title") val title: String? = null,

    @Schema(example = "null", description = "")
    @get:JsonProperty("authors") val authors: List<String>? = null,

    @Schema(example = "null", description = "")
    @get:JsonProperty("workPlace") val workPlace: String? = null,

    @Schema(example = "null", description = "")
    @get:JsonProperty("articleType") val articleType: String? = null,

    @Schema(example = "null", description = "")
    @get:JsonProperty("language") val language: String? = null,

    @Schema(example = "0", description = "")
    @get:JsonProperty("year") val year: Long? = null,

    @Schema(example = "null", description = "")
    @get:JsonProperty("journalTitle") val journalTitle: String? = null,

    @Schema(example = "0", description = "")
    @get:JsonProperty("number") val number: Long? = null,

    @field:Valid
    @Schema(example = "null", description = "")
    @get:JsonProperty("url") val url: java.net.URI? = null
)

