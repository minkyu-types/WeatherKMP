package com.samsung.weather_data.di

import com.samsung.weather_data.remote.api.GetCurrentAndForecastsWeatherApi
import com.samsung.weather_data.remote.api.createGetCurrentAndForecastsWeatherApi
import de.jensklingenberg.ktorfit.Ktorfit
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
class NetworkModule {

    @Single
    fun provideHttpClient(): HttpClient = HttpClient {
        install(ContentNegotiation) {
            json()
        }
    }

    @Single
    fun provideKtorfit(client: HttpClient): Ktorfit =
        Ktorfit.Builder()
            .baseUrl("https://api.openweathermap.org/data/3.0/onecall/")
            .httpClient(client)
            .build()

    @Single
    fun provideGetCurrentAndForecastsWeatherApi(ktorfit: Ktorfit): GetCurrentAndForecastsWeatherApi =
        ktorfit.createGetCurrentAndForecastsWeatherApi()
}