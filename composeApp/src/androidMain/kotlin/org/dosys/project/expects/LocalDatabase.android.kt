package org.dosys.project.expects

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import org.dosys.todo_data.local.TodoDatabase

actual object TodoDatabaseConstructor {
    private lateinit var appContext: Context

    fun provideApplicationContext(context: Context) {
        appContext = context.applicationContext
    }

    actual fun initialize(): RoomDatabase.Builder<TodoDatabase> {
        if (!::appContext.isInitialized) {
            throw IllegalStateException("Application context is not initialized. Call provideApplicationContext() first.")
        }

        return Room.databaseBuilder(
            appContext,
            TodoDatabase::class.java,
            "todo_db",
        ).fallbackToDestructiveMigration(false)
    }
}