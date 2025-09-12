package com.samsung.weather_data.remote.api

import com.samsung.weather_data.remote.model.GetWeatherOverviewDto
import com.samsung.weather_data.remote.model.type.WeatherUnit
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.Query

interface GetWeatherOverviewApi {

    @GET("overview")
    suspend fun getWeatherOverview(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") appid: String,
        @Query("date") date: String?,
        @Query("units") units: String? = WeatherUnit.DEFAULT.name
    ): GetWeatherOverviewDto
}