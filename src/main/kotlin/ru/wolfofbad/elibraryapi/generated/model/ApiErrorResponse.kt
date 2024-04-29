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
    @get:JsonProperty("description") val description: kotlin.String? = null,

    @Schema(example = "null", description = "")
    @get:JsonProperty("code") val code: kotlin.String? = null,

    @Schema(example = "null", description = "")
    @get:JsonProperty("exceptionName") val exceptionName: kotlin.String? = null,

    @Schema(example = "null", description = "")
    @get:JsonProperty("exceptionMessage") val exceptionMessage: kotlin.String? = null,

    @Schema(example = "null", description = "")
    @get:JsonProperty("stacktrace") val stacktrace: kotlin.collections.List<kotlin.String>? = null
) {

}

