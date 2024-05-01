package ru.wolfofbad.elibraryapi.generated.model


import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema

/**
 *
 * @param data
 */
data class AddArticleRequest(

    @Schema(example = "null", description = "")
    @get:JsonProperty("data") val data: List<String>? = null
) {

}
