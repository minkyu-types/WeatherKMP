package org.dosys.project.util

import io.ktor.client.plugins.api.createClientPlugin
import io.ktor.client.request.HttpRequestBuilder

object OpenWeatherClientPlugin {
    class Config { lateinit var apiKey: String }

    val Plugin = createClientPlugin("OpenWeatherAppId", OpenWeatherClientPlugin::Config) {
        val key = pluginConfig.apiKey
        onRequest { request, _ ->
            if (shouldAttach(request)) {
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