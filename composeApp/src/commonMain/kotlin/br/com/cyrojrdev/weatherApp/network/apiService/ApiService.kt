package br.com.cyrojrdev.weatherApp.network.apiService

import br.com.cyrojrdev.weatherApp.network.model.WeatherResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class ApiService(
    val httpClient: HttpClient,
) {
    suspend fun getWeatherDataByCity(city: String): Result<WeatherResponse> =
        try {
            val response = httpClient
                .get("api/weather/by-city") {
                    url {
                        parameter("city", city)
                    }
                }.body<WeatherResponse>()
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
}
