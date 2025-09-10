package com.samsung.weather_data.remote.api

import com.samsung.weather_data.remote.model.GetCurrentAndForecastsWeatherDto
import com.samsung.weather_data.remote.model.type.WeatherUnit
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.Query

interface GetCurrentAndForecastsWeatherApi {

    @GET("data/3.0/onecall")
    suspend fun getCurrentAndForecastsWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") appid: String,
        @Query("exclude") exclude: String? = null,
        @Query("units") units: String? = WeatherUnit.DEFAULT.name,
        @Query("lang") lang: String?
    ): GetCurrentAndForecastsWeatherDto
}