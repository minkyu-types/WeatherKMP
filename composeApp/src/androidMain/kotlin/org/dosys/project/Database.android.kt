package org.dosys.project

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.samsung.weather_data.local.TodoDatabase

fun getTodoDatabaseBuilder(context: Context): RoomDatabase.Builder<TodoDatabase> {
    val appContext = context.applicationContext
    val dbFile = appContext.getDatabasePath("todo.db")
    return Room.databaseBuilder<TodoDatabase>(
        context = appContext,
        name = dbFile.absolutePath
    )
}