package com.samsung.weather_data.repositoryImpl

import com.samsung.weather_data.remote.api.GetCurrentAndForecastsWeatherApi
import com.samsung.weather_data.remote.api.GetDailyAggregationApi
import com.samsung.weather_data.remote.api.GetWeatherByTimestampApi
import com.samsung.weather_data.remote.api.GetWeatherOverviewApi
import com.samsung.weather_data.remote.mapper.CurrentAndForecastsWeatherMapper
import com.samsung.weather_data.remote.mapper.DailyAggregationMapper
import com.samsung.weather_data.remote.mapper.WeatherByTimestampMapper
import com.samsung.weather_data.remote.mapper.WeatherOverviewMapper
import com.samsung.weather_data.toDomainException
import org.dosys.weather_domain.model.CurrentWeather
import org.dosys.weather_domain.model.DailyAggregation
import org.dosys.weather_domain.model.ExcludeType
import org.dosys.weather_domain.model.TimestampWeather
import org.dosys.weather_domain.model.WeatherOverview
import org.dosys.weather_domain.repository.WeatherRepository

class WeatherRepositoryImpl(
    private val getCurrentAndForecastsWeatherApi: GetCurrentAndForecastsWeatherApi,
    private val getDailyAggregationApi: GetDailyAggregationApi,
    private val getWeatherByTimestampApi: GetWeatherByTimestampApi,
    private val getWeatherOverviewApi: GetWeatherOverviewApi,
    private val currentAndForecastsWeatherMapper: CurrentAndForecastsWeatherMapper,
    private val dailyAggregationMapper: DailyAggregationMapper,
    private val weatherByTimestampMapper: WeatherByTimestampMapper,
    private val weatherOverviewMapper: WeatherOverviewMapper
): WeatherRepository {

    override suspend fun getCurrentWeather(
        lat: Double,
        lon: Double,
        exclude: List<ExcludeType>?,
        units: String?,
        lang: String?
    ): CurrentWeather {
        return try {
            val result = getCurrentAndForecastsWeatherApi.getCurrentAndForecastsWeather(
                lat = lat,
                lon = lon,
                appid = "",
                exclude = exclude?.joinToString(", ") { it.value },
                units = units,
                lang = lang
            )
            currentAndForecastsWeatherMapper.mapToDomain(result)
        } catch (e: Throwable) {
            throw e.toDomainException()
        }
    }

    override suspend fun getDailyAggregation(
        lat: Double,
        lon: Double,
        date: String,
        units: String?,
        lang: String?
    ): DailyAggregation {
        return try {
            val result = getDailyAggregationApi.getDailyAggregation(
                lat = lat,
                lon = lon,
                date = date,
                appid = "",
                units = units,
                lang = lang
            )
            dailyAggregationMapper.mapToDomain(result)
        } catch (e: Throwable) {
            throw e.toDomainException()
        }
    }

    override suspend fun getWeatherFromLLM(prompt: String) {
        TODO("Not yet implemented")
    }

    override suspend fun getWeatherByTimestamp(
        lat: Double,
        lon: Double,
        dt: Long,
        units: String?,
        lang: String?
    ): TimestampWeather {
        return try {
            val result = getWeatherByTimestampApi.getWeatherByTimeStamp(
                lat = lat,
                lon = lon,
                dt = dt,
                appid = "",
                units = units,
                lang = lang
            )
            weatherByTimestampMapper.mapToDomain(result)
        } catch (e: Throwable) {
            throw e.toDomainException()
        }
    }

    override suspend fun getWeatherOverview(
        lat: Double,
        lon: Double,
        date: String,
        units: String?
    ): WeatherOverview {
        return try {
            val result = getWeatherOverviewApi.getWeatherOverview(
                lat = lat,
                lon = lon,
                appid = "",
                date = date,
                units = units
            )
            weatherOverviewMapper.mapToDomain(result)
        } catch (e: Throwable) {
            throw e.toDomainException()
        }
    }
}