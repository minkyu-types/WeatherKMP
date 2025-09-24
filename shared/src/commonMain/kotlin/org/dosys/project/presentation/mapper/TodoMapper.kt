package org.dosys.project.presentation.mapper

import org.dosys.project.presentation.feature.todo.TodoModel
import org.dosys.todo_domain.model.Todo

class TodoMapper {

    fun mapToDomain(todo: TodoModel): Todo = Todo(
        id = todo.id,
        content = todo.content,
        createdAt = todo.createdAt,
        updatedAt = todo.updatedAt,
        isComplete = todo.isComplete
    )

    fun mapToPresentation(todo: Todo): TodoModel = TodoModel(
        id = todo.id,
        content = todo.content,
        createdAt = todo.createdAt,
        updatedAt = todo.updatedAt,
        isComplete = todo.isComplete
    )
}