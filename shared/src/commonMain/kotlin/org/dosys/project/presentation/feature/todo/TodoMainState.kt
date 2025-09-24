package org.dosys.project.presentation.feature.todo

import androidx.paging.PagingData
import org.dosys.project.presentation.feature.base.BaseState
import org.dosys.project.presentation.feature.base.LoadState

data class TodoMainState(
    override val loadState: LoadState = LoadState.Idle,
    val todos: List<TodoModel> = emptyList(),
    val pagedTodos: PagingData<TodoModel> = PagingData.empty(),
    val sortType: TodoSortType = TodoSortType.RECENT_CREATED,
): BaseState {

    enum class TodoSortType {
        RECENT_CREATED,
        RECENT_UPDATED,
        ALPHABETICAL_ASC,
        ALPHABETICAL_DESC,
    }
}