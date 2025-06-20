package br.com.cyrojrdev.weatherApp.network.di

import br.com.cyrojrdev.weatherApp.network.apiService.ApiService
import br.com.cyrojrdev.weatherApp.network.client.KtorClient
import io.ktor.client.HttpClient
import org.koin.dsl.module

fun coreNetworkCoreModule() =
    module {
        single<HttpClient> { KtorClient.getInstance() }
        single { ApiService(get()) }
    }
