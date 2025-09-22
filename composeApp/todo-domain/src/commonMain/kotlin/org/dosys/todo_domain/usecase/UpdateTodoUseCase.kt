package org.dosys.todo_domain.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.dosys.base_domain.BaseSuspendUseCaseImpl
import org.dosys.todo_domain.model.Todo
import org.dosys.todo_domain.repository.TodoRepository

class UpdateTodoUseCase(
    private val repository: TodoRepository
) : BaseSuspendUseCaseImpl<Todo, Unit>(
    dispatcher = Dispatchers.IO
) {
    override suspend fun execute(input: Todo) {
        repository.updateTodo(input)
    }
}