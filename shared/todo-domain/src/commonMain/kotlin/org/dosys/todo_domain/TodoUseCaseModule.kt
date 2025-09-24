package org.dosys.todo_domain

import org.dosys.todo_domain.usecase.CreateTodoUseCase
import org.dosys.todo_domain.usecase.DeleteTodoUseCase
import org.dosys.todo_domain.usecase.GetTodosUseCase
import org.dosys.todo_domain.usecase.UpdateTodoUseCase
import org.koin.dsl.module

val todoUseCaseModule = module {
    single { GetTodosUseCase(get()) }
    single { CreateTodoUseCase(get()) }
    single { UpdateTodoUseCase(get()) }
    single { DeleteTodoUseCase(get()) }
}