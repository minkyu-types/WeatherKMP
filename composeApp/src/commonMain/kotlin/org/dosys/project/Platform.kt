package org.dosys.project
import com.samsung.weather_data.di.networkModule
import com.samsung.weather_data.di.repositoryModule
import com.samsung.weather_data.di.weatherDataModule
import org.dosys.project.presentation.di.coroutineModule
import org.dosys.project.presentation.di.mapperModule
import org.dosys.project.presentation.di.scopeModule
import org.dosys.project.presentation.di.storeModule
import org.dosys.project.presentation.di.usecaseModule
import org.dosys.project.presentation.di.viewModelModule
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

fun appModule() = listOf(
    storeModule,
    coroutineModule,
    networkModule,
    platformModule,
    weatherDataModule,
    scopeModule,
    usecaseModule,
    mapperModule,
    repositoryModule,
    viewModelModule
)