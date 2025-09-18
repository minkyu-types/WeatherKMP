package com.samsung.weather_data.remote.api

import com.samsung.weather_data.remote.model.WeathersFor5DaysResponseDto
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.Query

interface GetWeatherFor5DaysApi {

    @GET("forecast")
    suspend fun getWeathersFor5Days(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("mode") mode: String? = null,
        @Query("cnt") cnt: Int? = 40,
        @Query("units") units: String? = null,
        @Query("lang") lang: String? = null
    ): WeathersFor5DaysResponseDto
}