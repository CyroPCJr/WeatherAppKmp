package br.com.cyrojrdev.weatherApp.network.di

import br.com.cyrojrdev.weatherApp.network.apiService.ApiService
import br.com.cyrojrdev.weatherApp.network.client.KtorClient
import org.koin.dsl.module

fun coreNetworkCoreModule() =
    module {
        single {
            ApiService(
                httpClient = KtorClient.getInstance(),
            )
        }
    }
