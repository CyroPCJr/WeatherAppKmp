package br.com.cyrojrdev.weatherApp.network.model

import kotlinx.serialization.Serializable

@Serializable
data class Coord(
    val lat: Double,
    val lon: Double,
)
