package org.dosys.todo_domain.model

data class Todo(
    val id: Long = 1,
    val content: String,
    val createdAt: Long,
    val updatedAt: Long,
    val isComplete: Boolean
)
