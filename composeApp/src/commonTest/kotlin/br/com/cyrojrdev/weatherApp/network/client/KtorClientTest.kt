package br.com.cyrojrdev.weatherApp.network.client

import br.com.cyrojrdev.weatherApp.network.client.KtorClient
import br.com.cyrojrdev.weatherApp.network.di.coreNetworkCoreModule
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.pluginOrNull
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.test.runTest
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertSame

class KtorClientTest : KoinTest {
    @BeforeTest
    fun setUp() {
        startKoin {
            modules(coreNetworkCoreModule())
        }
    }

    @AfterTest
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `getHttpClient returns non null HttpClient instance`() {
        val ktorClient = KtorClient.getInstance()
        assertNotNull(ktorClient)
    }

    @Test
    fun `getHttpClient returns the same instance on multiple calls`() {
        val ktorInstance1 = KtorClient.getInstance()
        val ktorInstance2 = KtorClient.getInstance()
        assertSame(ktorInstance1, ktorInstance2)
    }

    @Test
    fun `getHttpClient when httpClient is initialized with a specific configuration`() {
        val ktorClient = KtorClient.getInstance()
        val installed = ktorClient.pluginOrNull(ContentNegotiation)
        assertNotNull(installed)
    }

    @Test
    fun `getHttpClient in a multithreaded environment`() =
        runTest {
            val results = mutableListOf<Deferred<HttpClient>>()

            repeat(100) {
                results += async {
                    KtorClient.getInstance()
                }
            }

            val clients = results.awaitAll()
            val first = clients.first()
            clients.forEach {
                assertSame(first, it, "Expected all instances to be the same (singleton)")
            }
        }
}
