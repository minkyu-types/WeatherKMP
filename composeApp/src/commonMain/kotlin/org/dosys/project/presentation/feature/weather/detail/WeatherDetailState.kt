package org.dosys.project.presentation.feature.weather.detail

import org.dosys.project.presentation.feature.base.BaseState
import org.dosys.project.presentation.feature.base.LoadState

data class WeatherDetailState(
    override val loadState: LoadState = LoadState.Idle,
): BaseState