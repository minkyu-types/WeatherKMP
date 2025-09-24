package org.dosys.project

import org.dosys.project.presentation.di.coroutineModule
import org.dosys.project.presentation.di.composeAppMapperModule
import org.dosys.project.presentation.di.networkModule
import org.dosys.project.presentation.di.scopeModule
import org.dosys.project.presentation.di.storeModule
import org.dosys.project.presentation.di.viewModelModule
import org.dosys.todo_domain.todoUseCaseModule
import org.dosys.weather_domain.weatherUsecaseModule
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

private val platformModule = module {
    singleOf(::Platform)
}

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect class Platform() {
    val name: String
}

fun getPlatform(): String = Platform().name

expect fun getRepositoryModule(): List<Module>

fun appModule() = listOf(
    platformModule,

    storeModule,
    viewModelModule,
    coroutineModule,
    composeAppMapperModule,

    networkModule,
    scopeModule,

    todoUseCaseModule,
    weatherUsecaseModule,

) + getRepositoryModule()