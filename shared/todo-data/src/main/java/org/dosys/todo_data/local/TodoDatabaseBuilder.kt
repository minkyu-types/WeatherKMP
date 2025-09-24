package org.dosys.todo_data.local

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

fun getTodoDatabaseBuilder(appContext: Context): RoomDatabase.Builder<TodoDatabase> {
    val dbFile = appContext.getDatabasePath("todo.db")
    return Room.databaseBuilder<TodoDatabase>(
        context = appContext,
        name = dbFile.absolutePath
    )
}