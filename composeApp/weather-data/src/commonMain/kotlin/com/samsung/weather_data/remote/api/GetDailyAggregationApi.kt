package com.samsung.weather_data.remote.api

import com.samsung.weather_data.remote.model.GetDailyAggregationDto
import com.samsung.weather_data.remote.model.type.WeatherUnit
import de.jensklingenberg.ktorfit.http.Query

interface GetDailyAggregationApi {

    suspend fun getDailyAggregation(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("date") date: String,
        @Query("appid") appid: String,
        @Query("units") units: String? = WeatherUnit.DEFAULT.name,
        @Query("lang") lang: String?
    ): GetDailyAggregationDto
}