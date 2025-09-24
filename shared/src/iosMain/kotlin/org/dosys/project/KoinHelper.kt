package org.dosys.project

import com.samsung.weather_data.di.weatherDataModule
import org.dosys.todo_data.di.todoDataModule
import org.dosys.todo_data.local.TodoDatabase
import org.dosys.todo_data.local.getTodoDatabaseBuilder
import org.koin.core.context.startKoin
import org.koin.dsl.module

fun doInitKoin() {
    startKoin {
        modules(
            appModule()
                    + weatherDataModule
                    + todoDataModule
                    + getTodoDatabaseModule()
                    + getRepositoryModule()
        )
    }
}

private fun getTodoDatabaseModule() = module {
    single<TodoDatabase> {
        getTodoDatabaseBuilder()
            .build()
    }
}