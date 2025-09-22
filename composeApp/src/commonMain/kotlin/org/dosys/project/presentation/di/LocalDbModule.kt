package org.dosys.project.presentation.di

import org.dosys.project.expects.getTodoDatabase
import org.dosys.todo_data.local.TodoDatabase
import org.koin.dsl.module

val localDbModule = module {
    single<TodoDatabase> { getTodoDatabase() }
}