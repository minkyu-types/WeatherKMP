package org.dosys.project

import com.samsung.weather_data.di.weatherDataModule
import org.dosys.todo_data.di.todoDataModule
import org.dosys.todo_data.local.TodoDatabase
import org.dosys.todo_data.local.getTodoDatabase
import org.dosys.todo_data.local.getTodoDatabaseBuilder
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class Platform actual constructor() {
    actual val name: String = "Android"
}

actual fun getRepositoryModule(): List<Module> {
    return todoDataModule + weatherDataModule + getTodoDatabaseModule()
}

private fun getTodoDatabaseModule() = module {
    single<TodoDatabase> {
        getTodoDatabase(getTodoDatabaseBuilder(androidContext()))
    }
}