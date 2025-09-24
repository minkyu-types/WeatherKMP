package org.dosys.project.util

import org.dosys.weather_domain.model.Language

fun getWindDirectionText(language: Language, deg: Int): String {
    val directionsKR = listOf(
        "북", "북북동", "북동", "동북동",
        "동", "동남동", "남동", "남남동",
        "남", "남남서", "남서", "서남서",
        "서", "서북서", "북서", "북북서"
    )
    val directionsEN = listOf(
        "N", "NNE", "NE", "ENE",
        "E", "ESE", "SE", "SSE",
        "S", "SSW", "SW", "WSW",
        "W", "WNW", "NW", "NNW"
    )
    val index = ((deg % 360) / 22.5).toInt()
    return when (language) {
        Language.KR -> directionsKR[index % 16]
        Language.EN -> directionsEN[index % 16]
        else -> ""
    }
}