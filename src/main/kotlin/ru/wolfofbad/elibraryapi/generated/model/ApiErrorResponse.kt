package ru.wolfofbad.elibraryapi.generated.model

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema

/**
 *
 * @param description
 * @param code
 * @param exceptionName
 * @param exceptionMessage
 * @param stacktrace
 */
data class ApiErrorResponse(

    @Schema(example = "null", description = "")
    @get:JsonProperty("description") val description: String? = null,

    @Schema(example = "null", description = "")
    @get:JsonProperty("code") val code: String? = null,

    @Schema(example = "null", description = "")
    @get:JsonProperty("exceptionName") val exceptionName: String? = null,

    @Schema(example = "null", description = "")
    @get:JsonProperty("exceptionMessage") val exceptionMessage: String? = null,

    @Schema(example = "null", description = "")
    @get:JsonProperty("stacktrace") val stacktrace: List<String>? = null
)

