package org.dosys.weather_domain.model

data class Todo(
    val id: Long = 1,
    val title: String,
    val content: String,
    val createdAt: Long,
    val updatedAt: Long
)
