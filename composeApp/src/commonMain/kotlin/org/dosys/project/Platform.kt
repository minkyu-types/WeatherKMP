package org.dosys.project
import com.samsung.weather_data.di.weatherDataModule
import org.dosys.project.presentation.di.commonModule
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val platformModule = module {
    singleOf(::Platform)
}

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect class Platform() {
    val name: String
}

fun getPlatform(): String = Platform().name

fun appModule() = listOf(
    commonModule,
    platformModule,
    weatherDataModule
)