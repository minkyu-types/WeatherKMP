package org.dosys.project.presentation.di

import org.dosys.project.presentation.feature.todo.TodoMainViewModel
import org.dosys.project.presentation.feature.weather.main.WeatherMainViewModel
import org.koin.dsl.module

val viewModelModule = module {
    single<WeatherMainViewModel> { WeatherMainViewModel(get()) }
    single<TodoMainViewModel> { TodoMainViewModel(get()) }
}