package org.dosys.weather_domain.repository

import kotlinx.coroutines.flow.Flow
import org.dosys.weather_domain.model.Todo

interface TodoRepository {

    fun getTodos(): Flow<List<Todo>>
    suspend fun createTodo(todo: Todo)
    suspend fun updateTodo(todo: Todo)
    suspend fun deleteTodo(todo: Todo)
}