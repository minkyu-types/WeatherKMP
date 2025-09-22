package com.samsung.weather_data.repositoryImpl

import com.samsung.weather_data.local.TodoDao
import com.samsung.weather_data.local.mapper.TodoMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.dosys.weather_domain.model.Todo
import org.dosys.weather_domain.repository.TodoRepository

internal class TodoRepositoryImpl(
    private val todoDao: TodoDao,
    private val todoMapper: TodoMapper
) : TodoRepository {

    override fun getTodos(): Flow<List<Todo>> {
        return try {
            todoDao.getAllAsFlow().map { item ->
                item.map {
                    todoMapper.mapToDomain(it)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }

    override suspend fun createTodo(todo: Todo) {
        val todoEntity = todoMapper.mapToData(todo)
        todoDao.insert(todoEntity)
    }

    override suspend fun updateTodo(todo: Todo) {
        val todoEntity = todoMapper.mapToData(todo)
        todoDao.upsert(todoEntity)
    }

    override suspend fun deleteTodo(todo: Todo) {
        val todoEntity = todoMapper.mapToData(todo)
        todoDao.delete(todoEntity)
    }
}