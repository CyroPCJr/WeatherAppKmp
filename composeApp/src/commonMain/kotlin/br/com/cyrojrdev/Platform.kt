package br.com.cyrojrdev

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
