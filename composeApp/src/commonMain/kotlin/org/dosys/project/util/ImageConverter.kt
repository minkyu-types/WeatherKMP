package org.dosys.project.util

object ImageConverter {

    fun openWeatherIconUrl(icon: String, size: Int = 2): String {
        val suffix = when (size) {
            1 -> ""     // 1x
            2 -> "@2x"  // 2x
            4 -> "@4x"  // 4x
            else -> "@2x"
        }
        return "https://openweathermap.org/img/wn/$icon$suffix.png"
    }
}