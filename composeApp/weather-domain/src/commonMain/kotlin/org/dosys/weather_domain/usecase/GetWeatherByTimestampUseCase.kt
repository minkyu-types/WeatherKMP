package org.dosys.weather_domain.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.dosys.weather_domain.base.BaseFlowUseCaseImpl
import org.dosys.weather_domain.model.TimestampWeather
import org.dosys.weather_domain.repository.WeatherRepository
import org.dosys.weather_domain.usecase.GetWeatherByTimestampUseCase.GetWeatherByTimestampParams

class GetWeatherByTimestampUseCase(
    repository: WeatherRepository
): BaseFlowUseCaseImpl<GetWeatherByTimestampParams, Flow<TimestampWeather>, WeatherRepository>(
    repository = repository,
    dispatcher = Dispatchers.IO
) {
    override suspend fun execute(input: GetWeatherByTimestampParams): Flow<TimestampWeather> {
        return flow {
            repository.getWeatherByTimestamp(
                input.lat,
                input.lon,
                input.dt,
                input.units,
                input.lang
            )
        }
    }

    override fun onError(e: Throwable): Flow<TimestampWeather> {
        return super.onError(e)
    }

    data class GetWeatherByTimestampParams(
        val lat: Double,
        val lon: Double,
        val dt: Long,
        val units: String?,
        val lang: String?
    )
}