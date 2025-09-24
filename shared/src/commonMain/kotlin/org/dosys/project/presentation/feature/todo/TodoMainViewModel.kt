package org.dosys.project.presentation.feature.todo

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.dosys.project.util.SharedViewModel

class TodoMainViewModel(
    factory: TodoMainStoreFactory
) : SharedViewModel() {

    private val store = factory.create(viewModelScope)
    val pagingFlow = store.pagedTodos

    val state: StateFlow<TodoMainState> = store.state
    val effect: Flow<TodoMainSideEffect> = store.effect

    init {
        store.observeTodos()
    }

    fun addTodo(todo: TodoModel) {
        store.createTodo(todo)
    }

    fun editTodo(todo: TodoModel) {
        store.updateTodo(todo)
    }

    fun deleteTodo(todo: TodoModel) {
        store.deleteTodo(todo)
    }
}