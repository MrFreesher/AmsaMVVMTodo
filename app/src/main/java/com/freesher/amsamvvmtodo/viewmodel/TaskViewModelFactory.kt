package com.freesher.amsamvvmtodo.viewmodel


import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.freesher.amsamvvmtodo.database.TaskDAO
import com.freesher.amsamvvmtodo.database.TaskRepository


class TaskViewModelFactory(
    private val database: TaskRepository,
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TaskViewModel::class.java)) {
            return TaskViewModel(database, application) as T
        }
        throw IllegalArgumentException("Unknown viewmodel class")
    }

}
