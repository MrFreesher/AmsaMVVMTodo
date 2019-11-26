package com.freesher.amsamvvmtodo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.freesher.amsamvvmtodo.database.TaskDatabase
import com.freesher.amsamvvmtodo.database.TaskRepository
import com.freesher.amsamvvmtodo.model.Task
import com.freesher.amsamvvmtodo.viewmodel.TaskViewModel
import com.freesher.amsamvvmtodo.viewmodel.TaskViewModelFactory
import kotlinx.android.synthetic.main.activity_add_task.*
import java.text.SimpleDateFormat
import java.util.*

class AddTaskActivity : AppCompatActivity() {
    private lateinit var taskViewModelFactory: TaskViewModelFactory
    private lateinit var taskViewModel: TaskViewModel
    private lateinit var database: TaskRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)
        database = TaskRepository(application)
        taskViewModelFactory = TaskViewModelFactory(database,this.application)
        taskViewModel = ViewModelProviders.of(this,taskViewModelFactory).get(TaskViewModel::class.java)
        addTaskBtn.setOnClickListener { addTask() }
    }
    fun addTask(){
        val title = titleInput.text.toString()
        val description = descriptionInput.text.toString()
        val sdf = SimpleDateFormat("dd.MM.yyyy")
        val currentDate: String = sdf.format(Date())
        val priority = when(priorityRadio.checkedRadioButtonId){
            R.id.HighPriority -> 3
            R.id.MediumPriority -> 2
            else -> 1
        }
        val task = Task(title = title,description = description,priority = priority,date = currentDate)
        taskViewModel.insertTask(task)

        Toast.makeText(this,"Added",Toast.LENGTH_SHORT).show()
        clearFields()
    }
    fun clearFields(){
        titleInput.setText("")
        descriptionInput.setText("")
        priorityRadio.clearCheck()
    }
}
