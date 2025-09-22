package org.dosys.project.expects

import androidx.room.RoomDatabaseConstructor
import com.samsung.weather_data.local.TodoDatabase

@Suppress("KotlinNoActualForExpect")
expect object AppDatabaseConstructor(): RoomDatabaseConstructor<TodoDatabase> {
    override fun initialize(): TodoDatabase
}