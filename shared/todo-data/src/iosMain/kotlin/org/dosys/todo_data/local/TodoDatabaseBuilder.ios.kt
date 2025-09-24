package org.dosys.todo_data.local

import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask

//@Suppress("KotlinNoActualForExpect")
//actual object TodoDatabaseConstructor : RoomDatabaseConstructor<TodoDatabase> {
//    actual override fun initialize(): TodoDatabase {
//        return getDatabaseBuilder()
//            .build()
//    }
//}
//
fun getTodoDatabaseBuilder(): RoomDatabase.Builder<TodoDatabase> {
    val dbFilePath = documentDirectory() + "/todo.db"
    return Room.databaseBuilder<TodoDatabase>(
        name = dbFilePath,
    ).fallbackToDestructiveMigration(false)
}

@OptIn(ExperimentalForeignApi::class)
private fun documentDirectory(): String {
    val documentDirectory = NSFileManager.defaultManager.URLForDirectory(
        directory = NSDocumentDirectory,
        inDomain = NSUserDomainMask,
        appropriateForURL = null,
        create = false,
        error = null,
    )
    return requireNotNull(documentDirectory?.path)
}