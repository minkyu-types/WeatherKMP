package org.dosys.todo_data.local

import org.dosys.todo_domain.model.Todo

internal class TodoMapper {

    fun mapToDomain(todoEntity: TodoEntity): Todo = Todo(
        id = todoEntity.id,
        title = todoEntity.title,
        content = todoEntity.content,
        createdAt = todoEntity.createdAt,
        updatedAt = todoEntity.updatedAt
    )

    fun mapToData(todo: Todo): TodoEntity = TodoEntity(
        id = todo.id,
        title = todo.title,
        content = todo.content,
        createdAt = todo.createdAt,
        updatedAt = todo.updatedAt
    )
}