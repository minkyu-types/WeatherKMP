package org.dosys.weather_domain.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.dosys.base_domain.BaseFlowUseCaseImpl
import org.dosys.weather_domain.model.ExcludeType
import org.dosys.weather_domain.model.WeathersFor5Days
import org.dosys.weather_domain.repository.WeatherRepository

class GetWeathersFor5DaysUseCase(
    private val repository: WeatherRepository,
): BaseFlowUseCaseImpl<GetWeathersFor5DaysUseCase.GetWeathersFor5DaysParams, Result<WeathersFor5Days>>(
    dispatcher = Dispatchers.IO
) {

    data class GetWeathersFor5DaysParams(
        val lat: Double,
        val lon: Double,
        val units: String?,
        val mode: List<ExcludeType>?,
        val cnt: Int? = 40,
        val lang: String?
    )

    override suspend fun execute(input: GetWeathersFor5DaysParams): Result<WeathersFor5Days> {
        return repository.getWeatherFor5Days(
            input.lat, input.lon,
            input.units, input.mode,
            input.cnt, input.lang
        )
    }
}