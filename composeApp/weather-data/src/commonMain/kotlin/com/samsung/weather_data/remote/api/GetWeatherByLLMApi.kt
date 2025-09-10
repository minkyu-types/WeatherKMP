package com.samsung.weather_data.remote.api

import com.samsung.weather_data.remote.model.GetWeatherByLLMDto
import de.jensklingenberg.ktorfit.http.Query

interface GetWeatherByLLMApi {

    suspend fun getWeatherByLLM(
       @Query("prompt") prompt: String
    ): GetWeatherByLLMDto
}