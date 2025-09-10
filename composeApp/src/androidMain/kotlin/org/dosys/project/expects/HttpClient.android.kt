package org.dosys.project.expects

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.header
import kotlinx.serialization.json.Json

actual val client: HttpClient = HttpClient(OkHttp) {
    install(HttpTimeout) {
        socketTimeoutMillis = 60_000L
        requestTimeoutMillis = 60_000L
    }
    defaultRequest {
        header("Content-type", "applicaiton/json")
        url("https://api.openweathermap.org/data/3.0/onecall")
    }
    install(ContentNegotiation) {
//        json(Json {
//            isLenient = true
//            ignoreUnknownKeys = true
//            explicitNulls = false
//        })
    }
}