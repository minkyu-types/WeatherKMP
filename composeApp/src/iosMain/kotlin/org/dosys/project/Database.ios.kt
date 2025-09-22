package org.dosys.project

import androidx.room.Room
import androidx.room.RoomDatabase
import com.samsung.weather_data.local.TodoDatabase
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask

fun getTodoDatabaseBuilder(): RoomDatabase.Builder<TodoDatabase> {
    val dbFilePath = documentDirectory() + "todo.db"
    return Room.databaseBuilder<TodoDatabase>(
        name = dbFilePath
    )
}

@OptIn(ExperimentalForeignApi::class)
private fun documentDirectory(): String {
    val documentDirectory = NSFileManager.defaultManager.URLForDirectory(
        directory = NSDocumentDirectory,
        inDomain = NSUserDomainMask,
        appropriateForURL = null,
        create = false,
        error = null
    )
    return requireNotNull(documentDirectory?.path)
}