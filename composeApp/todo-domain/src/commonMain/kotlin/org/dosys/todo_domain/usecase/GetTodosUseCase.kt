package org.dosys.todo_domain.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import org.dosys.base_domain.BaseFlowUseCaseImpl
import org.dosys.todo_domain.model.Todo
import org.dosys.todo_domain.repository.TodoRepository

class GetTodosUseCase(
    private val repository: TodoRepository,
): BaseFlowUseCaseImpl<Unit, Flow<List<Todo>>>(
    dispatcher = Dispatchers.IO
) {
    override suspend fun execute(input: Unit): Flow<List<Todo>> {
        return repository.getTodos()
    }
}