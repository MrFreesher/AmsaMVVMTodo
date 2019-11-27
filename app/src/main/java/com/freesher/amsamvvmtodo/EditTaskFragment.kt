package com.freesher.amsamvvmtodo


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.freesher.amsamvvmtodo.database.TaskRepository
import com.freesher.amsamvvmtodo.model.Task
import com.freesher.amsamvvmtodo.viewmodel.TaskViewModel
import com.freesher.amsamvvmtodo.viewmodel.TaskViewModelFactory
import kotlinx.android.synthetic.main.fragment_edit_task.*

/**
 * A simple [Fragment] subclass.
 */
class EditTaskFragment : Fragment() {
    private lateinit var taskViewModelFactory: TaskViewModelFactory
    private lateinit var taskViewModel: TaskViewModel
    private lateinit var database: TaskRepository
    private var taskId: Int? = null
    private lateinit var task: Task
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_edit_task, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        database = TaskRepository(activity!!.application)
        taskViewModelFactory = TaskViewModelFactory(database, activity!!.application)
        taskViewModel =
            ViewModelProviders.of(this, taskViewModelFactory).get(TaskViewModel::class.java)
        taskId = arguments?.getInt("id")
        task = database.getTask(taskId!!)
        fillFields(task)
        editTaskBtn.setOnClickListener { editTask() }
        doneTaskBtn.setOnClickListener { doneTask() }
    }

    fun editTask() {
        val title = titleInput.text.toString()

        val description = descriptionInput.text.toString()
        val priority = when (priorityRadio.checkedRadioButtonId) {
            R.id.HighPriority -> 3
            R.id.MediumPriority -> 2
            else -> 1
        }

        val newTask = Task(taskId, title, description, priority, task.status, task.date)
        taskViewModel.updateTask(newTask)

        Toast.makeText(activity, "Edited", Toast.LENGTH_SHORT).show()
    }

    fun doneTask() {
        task.status = "done"
        taskViewModel.updateTask(task)
        Toast.makeText(context, "Done", Toast.LENGTH_SHORT).show()
    }

    fun fillFields(task: Task) {
        titleInput.setText(task.title)
        descriptionInput.setText(task.description)
        when (task.priority) {
            3 -> priorityRadio.check(R.id.HighPriority)
            2 -> priorityRadio.check(R.id.MediumPriority)
            else -> priorityRadio.check(R.id.LowPriority)
        }


    }

}
