package org.dosys.project.presentation.feature.todo

import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import co.touchlab.kermit.Logger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.dosys.project.presentation.feature.base.BaseStore
import org.dosys.project.presentation.feature.base.LoadState
import org.dosys.project.presentation.mapper.TodoMapper
import org.dosys.todo_domain.usecase.CreateTodoUseCase
import org.dosys.todo_domain.usecase.DeleteTodoUseCase
import org.dosys.todo_domain.usecase.GetTodosUseCase
import org.dosys.todo_domain.usecase.UpdateTodoUseCase

class TodoMainStore(
    private val getTodosUseCase: GetTodosUseCase,
    private val createTodoUseCase: CreateTodoUseCase,
    private val updateTodoUseCase: UpdateTodoUseCase,
    private val deleteTodoUseCase: DeleteTodoUseCase,
    private val todoMapper: TodoMapper,
    scope: CoroutineScope
) : BaseStore<TodoMainState, TodoMainSideEffect>(
    scope = scope,
    initialState = TodoMainState()
) {

    val pagedTodos: Flow<PagingData<TodoModel>> =
        getTodosUseCase
            .pagedTodos()
            .map { pagingData ->
                pagingData.map { data ->
                    todoMapper.mapToPresentation(data)
                }
            }
            .cachedIn(storeScope)

    fun observePagedTodos() {
        storeScope.launch {
            pagedTodos.collectLatest { todos ->
                setState {
                    copy(
                        pagedTodos = todos
                    )
                }
            }
        }
    }


    fun observeTodos() {
        storeScope.launch {
            getTodosUseCase(Unit)
                .catch { e ->
                    e.printStackTrace()
                    postEffect(TodoMainSideEffect.ShowAlert("오류 발생"))
                }
                .collect { data ->
                    val todos = data.map { todo ->
                        todoMapper.mapToPresentation(todo)
                    }
                    setState {
                        copy(
                            todos = todos
                        )
                    }
                }
        }
    }

    fun createTodo(todoModel: TodoModel) {
        val todo = todoMapper.mapToDomain(todoModel)
        storeScope.launch {
            runCatching { createTodoUseCase(todo) }
                .onSuccess {
                    setState { copy(loadState = LoadState.Success) }
                    Logger.d("TodoMainStore: Todo 추가 성공")
                }
                .onFailure { e ->
                    e.printStackTrace()
                    setState { copy(loadState = LoadState.Error(e.message ?: "오류 발생")) }
                    postEffect(TodoMainSideEffect.ShowAlert("오류 발생"))
                }
        }
    }

    fun updateTodo(todoModel: TodoModel) {
        val todo = todoMapper.mapToDomain(todoModel)
        storeScope.launch {
            runCatching { updateTodoUseCase(todo) }
                .onSuccess {
                    setState { copy(loadState = LoadState.Success) }
                    // 업데이트 성공
                }
                .onFailure { e ->
                    e.printStackTrace()
                    setState { copy(loadState = LoadState.Error(e.message ?: "오류 발생")) }
                    postEffect(TodoMainSideEffect.ShowAlert("오류 발생"))
                }
        }
    }

    fun deleteTodo(todoModel: TodoModel) {
        val todo = todoMapper.mapToDomain(todoModel)
        storeScope.launch {
            runCatching { deleteTodoUseCase(todo) }
                .onSuccess {
                    setState { copy(loadState = LoadState.Success) }
                    // 삭제 성공
                }
                .onFailure { e ->
                    e.printStackTrace()
                    setState { copy(loadState = LoadState.Error(e.message ?: "오류 발생")) }
                    postEffect(TodoMainSideEffect.ShowAlert("오류 발생"))
                }
        }
    }
}