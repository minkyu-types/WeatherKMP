package org.dosys.todo_data.local

import org.dosys.todo_domain.model.Todo

internal class TodoMapper {

    fun mapToDomain(todoEntity: TodoEntity): Todo = Todo(
        id = todoEntity.id,
        content = todoEntity.content,
        createdAt = todoEntity.createdAt,
        updatedAt = todoEntity.updatedAt,
        isComplete = todoEntity.isComplete
    )

    fun mapToData(todo: Todo): TodoEntity = TodoEntity(
        id = todo.id,
        content = todo.content,
        createdAt = todo.createdAt,
        updatedAt = todo.updatedAt,
        isComplete = todo.isComplete
    )
}