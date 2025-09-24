package org.dosys.project.presentation.feature.todo

import kotlinx.coroutines.CoroutineScope
import org.dosys.project.presentation.mapper.TodoMapper
import org.dosys.todo_domain.usecase.CreateTodoUseCase
import org.dosys.todo_domain.usecase.DeleteTodoUseCase
import org.dosys.todo_domain.usecase.GetTodosUseCase
import org.dosys.todo_domain.usecase.UpdateTodoUseCase

class TodoMainStoreFactory(
    private val getTodosUseCase: GetTodosUseCase,
    private val createTodoUseCase: CreateTodoUseCase,
    private val updateTodoUseCase: UpdateTodoUseCase,
    private val deleteTodoUseCase: DeleteTodoUseCase,
    private val todoMapper: TodoMapper,
) {
    fun create(scope: CoroutineScope): TodoMainStore =
        TodoMainStore(
            getTodosUseCase = getTodosUseCase,
            createTodoUseCase = createTodoUseCase,
            updateTodoUseCase = updateTodoUseCase,
            deleteTodoUseCase = deleteTodoUseCase,
            todoMapper = todoMapper,
            scope = scope
        )
}