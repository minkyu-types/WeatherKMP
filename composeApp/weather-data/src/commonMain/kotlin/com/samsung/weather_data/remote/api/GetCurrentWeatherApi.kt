package com.samsung.weather_data.remote.api

import com.samsung.weather_data.remote.model.CurrentWeatherResponseDto
import com.samsung.weather_data.remote.model.type.WeatherUnit
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.Query

interface GetCurrentWeatherApi {

    @GET("weather")
    suspend fun getCurrentWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("mode") mode: String? = null,
        @Query("units") units: String? = WeatherUnit.DEFAULT.name,
        @Query("lang") lang: String? = null
    ): CurrentWeatherResponseDto
}