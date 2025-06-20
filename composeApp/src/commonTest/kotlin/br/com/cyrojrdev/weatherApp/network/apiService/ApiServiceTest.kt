package br.com.cyrojrdev.weatherApp.network.apiService

import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.engine.mock.respondError
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.get
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class ApiServiceTest : KoinTest {
    lateinit var apiService: ApiService

    val cityName = "London"
    val mockResponse =
        """
{
  "coord": {
    "lon": -0.1257,
    "lat": 51.5085
  },
  "weather": [
    {
      "id": 800,
      "main": "Clear",
      "description": "clear sky",
      "icon": "01d"
    }
  ],
  "base": "stations",
  "main": {
    "temp": 25.14,
    "feels_like": 25.02,
    "temp_min": 23.86,
    "temp_max": 26.98,
    "pressure": 1021,
    "humidity": 50,
    "sea_level": 1021,
    "grnd_level": 1017
  },
  "visibility": 10000,
  "wind": {
    "speed": 6.17,
    "deg": 80
  },
  "clouds": {
    "all": 7
  },
  "dt": 1750446017,
  "sys": {
    "type": 2,
    "id": 268730,
    "country": "GB",
    "sunrise": 1750390975,
    "sunset": 1750450873
  },
  "timezone": 3600,
  "id": 2643743,
  "name": "$cityName",
  "cod": 200
}
        """.trimIndent()

    @BeforeTest
    fun setUp() {
        val mockEngine = MockEngine { request ->
            when (request.url.encodedPath) {
                "/api/weather/by-city" -> respond(
                    content = mockResponse,
                    headers = headersOf(
                        HttpHeaders.ContentType,
                        ContentType.Application.Json.toString(),
                    ),
                )

                else -> respondError(HttpStatusCode.NotFound)
            }
        }

        val mockHttpClient = HttpClient(mockEngine) {
            install(ContentNegotiation) {
                json(Json { ignoreUnknownKeys = true })
            }
        }

        startKoin {
            modules(
                module {
                    single<HttpClient> { mockHttpClient }
                    single { ApiService(get()) }
                },
            )
        }

        apiService = get()
    }

    @AfterTest
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `getWeatherData returns success with expected data`() =
        runTest {
            val result = apiService.getWeatherDataByCity(city = cityName)
            assertTrue(result.isSuccess)
            val weather = result.getOrNull()
            assertNotNull(weather)
            assertEquals(cityName, weather.name)
        }
}
