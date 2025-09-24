package org.dosys.project.presentation.feature.todo

import kotlin.time.Clock
import kotlin.time.ExperimentalTime

data class TodoModel(
    val id: Long = 1,
    val content: String,
    val createdAt: Long,
    val updatedAt: Long,
    val isComplete: Boolean,
) {

    companion object {
        @OptIn(ExperimentalTime::class)
        fun createNewTodoModel(): TodoModel {
            val currTime = Clock.System.now().toEpochMilliseconds()
            return TodoModel(
                id = 0,
                content = "",
                createdAt = currTime,
                updatedAt = currTime,
                isComplete = false,
            )
        }
    }
}