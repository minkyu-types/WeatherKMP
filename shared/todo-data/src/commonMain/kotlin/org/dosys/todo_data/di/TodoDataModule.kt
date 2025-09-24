package org.dosys.todo_data.di

import org.dosys.todo_data.local.TodoDao
import org.dosys.todo_data.local.TodoDatabase
import org.dosys.todo_data.local.TodoMapper
import org.dosys.todo_data.repositoryImpl.TodoRepositoryImpl
import org.dosys.todo_domain.repository.TodoRepository
import org.koin.dsl.module

val todoDataModule = module {
    single<TodoMapper> { TodoMapper() }
    single<TodoDao> { get<TodoDatabase>().getDao() }

    single<TodoRepository> {
        TodoRepositoryImpl(
            todoDao = get(),
            todoMapper = get()
        )
    }
}