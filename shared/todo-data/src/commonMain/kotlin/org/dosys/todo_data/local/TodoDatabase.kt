package org.dosys.todo_data.local

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import io.ktor.util.Platform
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

@Database(entities = [TodoEntity::class], version = 1)
@ConstructedBy(TodoDatabaseConstructor::class)
abstract class TodoDatabase: RoomDatabase() {
    abstract fun getDao(): TodoDao
}

@Suppress("KotlinNoActualForExpect")
expect object TodoDatabaseConstructor : RoomDatabaseConstructor<TodoDatabase> {
    override fun initialize(): TodoDatabase
}

fun getTodoDatabase(
    builder: RoomDatabase.Builder<TodoDatabase>
): TodoDatabase {
    return builder
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}