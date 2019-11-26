package com.freesher.amsamvvmtodo.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.freesher.amsamvvmtodo.model.Task

@Dao
interface TaskDAO {
    @Insert
    suspend fun addTask(task: Task)

    @Update
    suspend fun editTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)

    @Query("SELECT * FROM task_table order by priority desc")
    fun getTasks(): LiveData<List<Task>>

    @Query("SELECT * FROM task_table WHERE id = :id")
    fun getTask(id: Int): Task
}