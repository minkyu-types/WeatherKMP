package org.dosys.project

import com.samsung.weather_data.di.weatherDataModule
import org.dosys.todo_data.di.todoDataModule
import org.koin.core.module.Module
import platform.UIKit.UIDevice

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class Platform actual constructor() {
    actual val name: String = UIDevice.currentDevice.systemName + " " + UIDevice.currentDevice.systemVersion
}

actual fun getRepositoryModule(): List<Module> {
    return todoDataModule + weatherDataModule
}