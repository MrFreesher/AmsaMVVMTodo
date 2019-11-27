package com.freesher.amsamvvmtodo.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.freesher.amsamvvmtodo.database.TaskDAO
import com.freesher.amsamvvmtodo.database.TaskRepository
import com.freesher.amsamvvmtodo.model.Task
import kotlinx.coroutines.*


class TaskViewModel(
    val database: TaskRepository,
    application: Application
) : AndroidViewModel(application) {


    val allTasks: LiveData<List<Task>>

    override fun onCleared() {
        super.onCleared()

    }

    init {
        allTasks = database.getAllTasks()
    }


    fun insertTask(task: Task) {
        database.addTask(task)
    }


    fun updateTask(task: Task) {
        database.updateTask(task)

    }


    fun deleteTask(task: Task) {


        database.deleteTask(task)


    }

}
