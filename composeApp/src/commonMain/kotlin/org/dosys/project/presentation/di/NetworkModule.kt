package org.dosys.project.presentation.di

import de.jensklingenberg.ktorfit.Ktorfit
import io.ktor.client.HttpClient
import org.dosys.project.expects.createHttpClient
import org.koin.dsl.module

val networkModule = module {
    single {
        createHttpClient()
    }

    single {
        Ktorfit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .httpClient(get<HttpClient>())
            .build()
    }
}