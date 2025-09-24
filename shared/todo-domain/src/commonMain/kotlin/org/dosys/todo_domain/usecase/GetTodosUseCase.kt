package org.dosys.todo_domain.usecase

import androidx.paging.PagingData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import org.dosys.todo_domain.model.Todo
import org.dosys.todo_domain.repository.TodoRepository

class GetTodosUseCase(
    private val repository: TodoRepository,
) {
    operator fun invoke(input: Unit): Flow<List<Todo>> {
        return repository.getTodos().flowOn(Dispatchers.IO)
    }

    fun pagedTodos(): Flow<PagingData<Todo>> {
        return repository.getPagedTodos().flowOn(Dispatchers.IO)
    }
}