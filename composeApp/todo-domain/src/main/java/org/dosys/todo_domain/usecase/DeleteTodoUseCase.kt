package org.dosys.weather_domain.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.dosys.weather_domain.base.BaseSuspendUseCaseImpl
import org.dosys.weather_domain.model.Todo
import org.dosys.weather_domain.repository.TodoRepository

class DeleteTodoUseCase(
    private val repository: TodoRepository
): BaseSuspendUseCaseImpl<Todo, Unit>(
    dispatcher = Dispatchers.IO
) {
    override suspend fun execute(input: Todo) {
        repository.deleteTodo(input)
    }
}