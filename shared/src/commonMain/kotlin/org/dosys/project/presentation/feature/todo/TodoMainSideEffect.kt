package org.dosys.project.presentation.feature.todo

import org.dosys.project.presentation.feature.base.BaseSideEffect

sealed class TodoMainSideEffect : BaseSideEffect {
    data class ShowAlert(val message: String) : TodoMainSideEffect()
    data class ShowSortBottomSheet(val sortType: TodoMainState.TodoSortType) : TodoMainSideEffect()
}