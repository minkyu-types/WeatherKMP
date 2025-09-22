package org.dosys.todo_data.repositoryImpl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.dosys.todo_data.local.TodoDao
import org.dosys.todo_data.local.TodoMapper
import org.dosys.todo_domain.model.Todo
import org.dosys.todo_domain.repository.TodoRepository

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