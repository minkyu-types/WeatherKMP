package com.samsung.weather_data

import io.ktor.client.plugins.api.createClientPlugin
import io.ktor.client.request.HttpRequestBuilder

object OpenWeatherAppId {
    class Config { lateinit var apiKey: String }

    val Plugin = createClientPlugin("OpenWeatherAppId", OpenWeatherAppId::Config) {
        val key = pluginConfig.apiKey
        onRequest { request, _ ->
            if (OpenWeatherAppId.shouldAttach(request)) {
                val params = request.url.parameters
                if (params["appid"].isNullOrBlank()) {
                    params.append("appid", key)
                }
            }
        }
    }

    private fun shouldAttach(request: HttpRequestBuilder): Boolean =
        request.url.host.endsWith("openweathermap.org")
}