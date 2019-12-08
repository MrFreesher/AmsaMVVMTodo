package com.freesher.amsamvvmtodo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.freesher.amsamvvmtodo.MainActivity
import com.freesher.amsamvvmtodo.R
import com.freesher.amsamvvmtodo.TaskListFragment
import com.freesher.amsamvvmtodo.model.Task
import com.freesher.amsamvvmtodo.viewmodel.TaskViewModel
import kotlinx.android.synthetic.main.list_row.view.*

class TaskAdapter(private val context: Context, private val viewModel: TaskViewModel) :
    RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {
    private var taskList: List<Task> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_row, parent, false)

        return TaskViewHolder(
            view
        )
    }

    override fun getItemCount(): Int = taskList.size

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.titleContent.text = taskList[position].title
        holder.statusContent.text = taskList[position].status
        when (taskList[position].priority) {
            3 -> holder.priorityContent.text = "High"
            2 -> holder.priorityContent.text = "Medium"
            else -> holder.priorityContent.text = "Low"
        }
        holder.dateContent.text = taskList[position].date
        holder.deleteBtn.setOnClickListener {
            viewModel.deleteTask(taskList[position])
        }
        holder.view.setOnClickListener {
            val mainActivity = context as MainActivity
            mainActivity.replaceFragment("edit", taskList[position].id!!)

        }
    }


    fun setTasks(newTaskList: List<Task>){
        taskList = newTaskList
        notifyDataSetChanged()
    }

    class TaskViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val titleContent: TextView = view.titleContent
        val statusContent: TextView = view.statusContent
        val dateContent: TextView = view.dateContent
        val priorityContent: TextView = view.priorityContent
        val deleteBtn: Button = view.removeTaskBtn

    }
}
