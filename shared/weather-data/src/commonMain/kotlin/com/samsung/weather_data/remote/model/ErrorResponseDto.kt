package com.samsung.weather_data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponseDto(
    val cod: Int,
    val message: String
)
