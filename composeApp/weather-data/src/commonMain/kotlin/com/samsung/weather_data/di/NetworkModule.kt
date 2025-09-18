package com.samsung.weather_data.di

import com.samsung.weather_data.OpenWeatherClientPlugin
import de.jensklingenberg.ktorfit.Ktorfit
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val networkModule = module {
    single {
        HttpClient {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    prettyPrint = true
                    isLenient = true
                })
            }

            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }

            install(OpenWeatherClientPlugin.Plugin) {
                apiKey = "33b8b53bc6db3b3c1519455c5795a6d5"
            }
        }
    }

    single {
        Ktorfit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .httpClient(get<HttpClient>())
            .build()
    }
}