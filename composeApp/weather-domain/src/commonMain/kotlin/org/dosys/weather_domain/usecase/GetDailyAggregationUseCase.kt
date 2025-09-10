package org.dosys.weather_domain.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.dosys.weather_domain.base.BaseFlowUseCaseImpl
import org.dosys.weather_domain.model.DailyAggregation
import org.dosys.weather_domain.repository.WeatherRepository
import org.dosys.weather_domain.usecase.GetDailyAggregationUseCase.GetDailyAggregationParams

class GetDailyAggregationUseCase(
    repository: WeatherRepository,
): BaseFlowUseCaseImpl<GetDailyAggregationParams, Flow<DailyAggregation>, WeatherRepository>(
    repository = repository,
    dispatcher = Dispatchers.IO
) {
    override suspend fun execute(input: GetDailyAggregationParams): Flow<DailyAggregation> {
        return flow {
            repository.getDailyAggregation(
                input.lat,
                input.lon,
                input.date,
                input.units,
                input.lang
            )
        }
    }

    data class GetDailyAggregationParams(
        val lat: Double,
        val lon: Double,
        val date: String,
        val units: String?,
        val lang: String?
    )
}