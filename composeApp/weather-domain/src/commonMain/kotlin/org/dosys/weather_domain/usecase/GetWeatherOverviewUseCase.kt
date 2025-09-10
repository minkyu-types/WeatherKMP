package org.dosys.weather_domain.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.dosys.weather_domain.base.BaseFlowUseCaseImpl
import org.dosys.weather_domain.model.WeatherOverview
import org.dosys.weather_domain.repository.WeatherRepository
import org.dosys.weather_domain.usecase.GetWeatherOverviewUseCase.*

class GetWeatherOverviewUseCase(
    repository: WeatherRepository
): BaseFlowUseCaseImpl<GetWeatherOverViewParams, Flow<WeatherOverview>, WeatherRepository>(
    repository = repository,
    dispatcher = Dispatchers.IO
) {

    override suspend fun execute(input: GetWeatherOverViewParams): Flow<WeatherOverview> {
        return flow {
            repository.getWeatherOverview(
                input.lat,
                input.lon,
                input.date,
                input.units
            )
        }
    }

    override fun onError(e: Throwable): Flow<WeatherOverview> {
        return super.onError(e)
    }

    data class GetWeatherOverViewParams(
        val lat: Double,
        val lon: Double,
        val date: String,
        val units: String?
    )
}