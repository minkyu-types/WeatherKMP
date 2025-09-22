package org.dosys.todo_data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TodoEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 1,
    val title: String,
    val content: String,
    val createdAt: Long,
    val updatedAt: Long
)
