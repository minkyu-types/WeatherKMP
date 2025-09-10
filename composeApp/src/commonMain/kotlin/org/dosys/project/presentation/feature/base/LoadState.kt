package org.dosys.project.presentation.feature.base

sealed interface LoadState {
    data object Idle: LoadState
    data object Loading: LoadState
    data class Error(val message: String): LoadState
}