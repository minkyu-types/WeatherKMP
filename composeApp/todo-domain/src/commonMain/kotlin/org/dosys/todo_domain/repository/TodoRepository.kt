package org.dosys.todo_domain.repository

import kotlinx.coroutines.flow.Flow
import org.dosys.todo_domain.model.Todo

interface TodoRepository {

    fun getTodos(): Flow<List<Todo>>
    suspend fun createTodo(todo: Todo)
    suspend fun updateTodo(todo: Todo)
    suspend fun deleteTodo(todo: Todo)
}