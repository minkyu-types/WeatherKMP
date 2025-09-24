package org.dosys.todo_data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {

    @Insert
    suspend fun insert(item: TodoEntity)

    @Update
    suspend fun upsert(item: TodoEntity)

    @Delete
    suspend fun delete(item: TodoEntity)

    @Query("SELECT * FROM TodoEntity")
    fun getAllAsFlow(): Flow<List<TodoEntity>>

    @Query("SELECT * FROM TodoEntity ORDER BY updatedAt DESC")
    fun getTodosPagingSource(): PagingSource<Int, TodoEntity>
}