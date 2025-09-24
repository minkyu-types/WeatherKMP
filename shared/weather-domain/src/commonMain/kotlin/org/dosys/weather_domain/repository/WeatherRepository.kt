package org.dosys.weather_domain.repository

import org.dosys.base_domain.BaseRepository
import org.dosys.weather_domain.model.CurrentWeather
import org.dosys.weather_domain.model.ExcludeType
import org.dosys.weather_domain.model.WeathersFor5Days

interface WeatherRepository: BaseRepository {

    suspend fun getCurrentWeather(
        lat: Double, lon: Double,
        mode: List<ExcludeType>?,
        units: String?,
        lang: String?
    ): Result<CurrentWeather>
    suspend fun getWeatherFor5Days(
        lat: Double, lon: Double,
        units: String?,
        mode: List<ExcludeType>?,
        cnt: Int?,
        lang: String?
    ): Result<WeathersFor5Days>
}