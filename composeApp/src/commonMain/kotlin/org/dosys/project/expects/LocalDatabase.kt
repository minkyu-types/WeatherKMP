@file:Suppress("KotlinNoActualForExpect")

package org.dosys.project.expects

import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.dosys.todo_data.local.TodoDatabase

expect object TodoDatabaseConstructor {
    fun initialize(): RoomDatabase.Builder<TodoDatabase>
}

fun getTodoDatabase(): TodoDatabase {
    val builder = TodoDatabaseConstructor.initialize()
    return builder
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}