package com.freesher.amsamvvmtodo.database

import android.app.Application
import androidx.lifecycle.LiveData
import com.freesher.amsamvvmtodo.model.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class TaskRepository(application: Application){
    private val taskDao: TaskDAO
    private val allTasks: LiveData<List<Task>>

    init {
        val database = TaskDatabase.getInstance(application.applicationContext)
        taskDao = database.taskDAO()
        allTasks = taskDao.getTasks()
    }
    fun addTask(task: Task){
        runBlocking { this.launch(Dispatchers.IO) {
            taskDao.addTask(task)
        } }
    }
    fun updateTask(task: Task){
        runBlocking {
            this.launch(Dispatchers.IO) {
                taskDao.editTask(task)
            }
        }
    }
    fun deleteTask(task: Task){
        runBlocking {
            this.launch(Dispatchers.IO) {
                taskDao.deleteTask(task)
            }
        }
    }
    fun getAllTasks():LiveData<List<Task>>{
        return allTasks
    }
    fun getTask(id:Int):Task{
        var task:Task? = null
        runBlocking {
            this.launch(Dispatchers.IO) {
            task = taskDao.getTask(id)
            }
        }
        return task!!
    }


}