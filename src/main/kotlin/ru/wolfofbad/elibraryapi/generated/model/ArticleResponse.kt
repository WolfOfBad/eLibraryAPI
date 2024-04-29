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
 * @param url
 */
data class ArticleResponse(
    @Schema(example = "0", description = "")
    @get:JsonProperty("eLibraryId") val eLibraryId: kotlin.Long? = null,

    @Schema(example = "null", description = "")
    @get:JsonProperty("title") val title: kotlin.String? = null,

    @Schema(example = "null", description = "")
    @get:JsonProperty("authors") val authors: kotlin.collections.List<kotlin.String>? = null,

    @Schema(example = "null", description = "")
    @get:JsonProperty("workPlace") val workPlace: kotlin.String? = null,

    @Schema(example = "null", description = "")
    @get:JsonProperty("articleType") val articleType: kotlin.String? = null,

    @Schema(example = "null", description = "")
    @get:JsonProperty("language") val language: kotlin.String? = null,

    @Schema(example = "0", description = "")
    @get:JsonProperty("year") val year: kotlin.Long? = null,

    @field:Valid
    @Schema(example = "null", description = "")
    @get:JsonProperty("url") val url: java.net.URI? = null
) {
}

