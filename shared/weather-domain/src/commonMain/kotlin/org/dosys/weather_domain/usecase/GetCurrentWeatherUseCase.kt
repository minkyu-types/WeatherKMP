package org.dosys.weather_domain.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.dosys.base_domain.BaseFlowUseCaseImpl
import org.dosys.weather_domain.model.CurrentWeather
import org.dosys.weather_domain.model.ExcludeType
import org.dosys.weather_domain.repository.WeatherRepository
import org.dosys.weather_domain.usecase.GetCurrentWeatherUseCase.GetCurrentWeatherParams

class GetCurrentWeatherUseCase(
    private val repository: WeatherRepository
): BaseFlowUseCaseImpl<GetCurrentWeatherParams, Result<CurrentWeather>>(
    dispatcher = Dispatchers.IO
) {
    override suspend fun execute(input: GetCurrentWeatherParams): Result<CurrentWeather> {
        return repository.getCurrentWeather(
            input.lat, input.lon,
            input.excludeTypes,
            input.units,
            input.language
        )
    }

    override fun onError(e: Throwable): Result<CurrentWeather> {
        e.printStackTrace()
        return super.onError(e)
    }

    data class GetCurrentWeatherParams(
        val lat: Double,
        val lon: Double,
        val excludeTypes: List<ExcludeType>? = emptyList(),
        val units: String?,
        val language: String?
    )
}