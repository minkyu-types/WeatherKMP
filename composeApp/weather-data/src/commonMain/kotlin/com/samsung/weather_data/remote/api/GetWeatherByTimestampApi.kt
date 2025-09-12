package com.samsung.weather_data.remote.api

import com.samsung.weather_data.remote.model.GetWeatherByTimeStampDto
import com.samsung.weather_data.remote.model.type.WeatherUnit
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.Query

interface GetWeatherByTimestampApi {

    @GET("timemachine")
    suspend fun getWeatherByTimeStamp(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("dt") dt: Long,
        @Query("appid") appid: String,
        @Query("units") units: String? = WeatherUnit.DEFAULT.name,
        @Query("lang") lang: String?
    ): GetWeatherByTimeStampDto
}