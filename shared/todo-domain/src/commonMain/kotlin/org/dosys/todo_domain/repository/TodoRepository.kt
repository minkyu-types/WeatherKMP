package org.dosys.todo_domain.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import org.dosys.todo_domain.model.Todo

interface TodoRepository {

    fun getPagedTodos(): Flow<PagingData<Todo>>
    fun getTodos(): Flow<List<Todo>>
    suspend fun createTodo(todo: Todo)
    suspend fun updateTodo(todo: Todo)
    suspend fun deleteTodo(todo: Todo)
}