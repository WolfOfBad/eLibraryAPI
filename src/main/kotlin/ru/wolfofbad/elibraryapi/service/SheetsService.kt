package ru.wolfofbad.elibraryapi.service

import com.google.api.services.sheets.v4.Sheets
import com.google.api.services.sheets.v4.model.ValueRange
import org.springframework.stereotype.Service
import ru.wolfofbad.elibraryapi.configuration.GoogleSheetsConfiguration
import ru.wolfofbad.elibraryapi.generated.model.AddArticleRequest

@Service
class SheetsService(
    private val sheets: Sheets,

    config: GoogleSheetsConfiguration
) {
    private val id = config.sheetId

    fun appendData(request: AddArticleRequest) {
        val appendBody = ValueRange()
            .setValues(
                listOf(
                    request.data ?: throw NullPointerException("Request data is null"),
                )
            )

        sheets.spreadsheets().values()
            .append(id, "A1", appendBody)
            .setValueInputOption("USER_ENTERED")
            .setInsertDataOption("INSERT_ROWS")
            .setIncludeValuesInResponse(true)
            .execute()

    }

}