package br.com.cyrojrdev.weatherApp.network.model

import kotlinx.serialization.Serializable

@Serializable
data class Clouds(
    val all: Int,
)
