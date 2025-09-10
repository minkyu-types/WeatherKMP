package org.dosys.weather_domain.repository

import org.dosys.weather_domain.base.BaseRepository
import org.dosys.weather_domain.model.CurrentWeather
import org.dosys.weather_domain.model.DailyAggregation
import org.dosys.weather_domain.model.ExcludeType
import org.dosys.weather_domain.model.TimestampWeather
import org.dosys.weather_domain.model.WeatherOverview

interface WeatherRepository: BaseRepository {

    suspend fun getCurrentWeather(
        lat: Double, lon: Double,
        exclude: List<ExcludeType>?,
        units: String?,
        lang: String?,
    ): CurrentWeather
    suspend fun getDailyAggregation(
        lat: Double, lon: Double,
        date: String,
        units: String?,
        lang: String?
    ): DailyAggregation
    suspend fun getWeatherFromLLM(
        prompt: String
    )
    suspend fun getWeatherByTimestamp(
        lat: Double, lon: Double,
        dt: Long,
        units: String?,
        lang: String?
    ): TimestampWeather
    suspend fun getWeatherOverview(
        lat: Double, lon: Double,
        date: String,
        units: String?
    ): WeatherOverview
}