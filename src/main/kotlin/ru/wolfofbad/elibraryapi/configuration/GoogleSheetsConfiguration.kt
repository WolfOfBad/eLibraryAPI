package ru.wolfofbad.elibraryapi.configuration

import com.google.api.client.auth.oauth2.Credential
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.json.gson.GsonFactory
import com.google.api.client.util.store.MemoryDataStoreFactory
import com.google.api.services.sheets.v4.Sheets
import com.google.api.services.sheets.v4.SheetsScopes
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import java.io.File
import java.io.InputStreamReader

@ConfigurationProperties(prefix = "gsheets", ignoreUnknownFields = false)
data class GoogleSheetsConfiguration(
    val credentials: File,
    val sheetId: String
) {
    @Bean
    fun authorizeCredential(): Credential {
        val json = credentials.inputStream()

        val clientSecrets = GoogleClientSecrets.load(GsonFactory.getDefaultInstance(), InputStreamReader(json))

        val scopes = listOf(SheetsScopes.SPREADSHEETS)

        val flow = GoogleAuthorizationCodeFlow.Builder(
            GoogleNetHttpTransport.newTrustedTransport(),
            GsonFactory.getDefaultInstance(),
            clientSecrets,
            scopes
        ).setDataStoreFactory(MemoryDataStoreFactory())
            .setAccessType("offline").build()
        val credential: Credential = AuthorizationCodeInstalledApp(flow, LocalServerReceiver()).authorize("user")

        return credential
    }

    @Bean
    fun sheets(credential: Credential): Sheets {
        return Sheets.Builder(
            GoogleNetHttpTransport.newTrustedTransport(),
            GsonFactory.getDefaultInstance(), credential
        )
            .setApplicationName("eLibraryApi")
            .build()
    }
}