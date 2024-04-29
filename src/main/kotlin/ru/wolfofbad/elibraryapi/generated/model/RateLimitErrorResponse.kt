package ru.wolfofbad.elibraryapi.generated.model

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema

/**
 *
 * @param description
 * @param message
 */
data class RateLimitErrorResponse(

    @Schema(example = "null", description = "")
    @get:JsonProperty("description") val description: String? = null,

    @Schema(example = "null", description = "")
    @get:JsonProperty("message") val message: String? = null
)
