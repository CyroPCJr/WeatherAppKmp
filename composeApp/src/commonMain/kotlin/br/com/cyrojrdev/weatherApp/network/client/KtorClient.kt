package br.com.cyrojrdev.weatherApp.network.client

import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.accept
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object KtorClient {
    private val client: HttpClient by lazy {
        HttpClient {
            install(ContentNegotiation) {
                json(
                    json = Json {
                        ignoreUnknownKeys = true
                    },
                )
            }

            install(DefaultRequest) {
                url {
                    host = "weather-api-latest.onrender.com/"
                    protocol = URLProtocol.HTTPS
                    contentType(ContentType.Application.Json)
                    accept(ContentType.Application.Json)
                }
            }

            install(HttpTimeout) {
                socketTimeoutMillis = 3000
                connectTimeoutMillis = 3000
                requestTimeoutMillis = 3000
            }
        }
    }

    fun getInstance(): HttpClient = client
}
