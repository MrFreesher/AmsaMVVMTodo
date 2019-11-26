package com.freesher.amsamvvmtodo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.freesher.amsamvvmtodo.R
import com.freesher.amsamvvmtodo.model.Task
import kotlinx.android.synthetic.main.list_row.view.*

class TaskAdapter(private val context: Context) :
    RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {
    private var taskList:List<Task> = emptyList<Task>()

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


    }
    fun setTasks(newTaskList: List<Task>){
        taskList = newTaskList
        notifyDataSetChanged()
    }

    class TaskViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleContent: TextView = view.titleContent
        val statusContent: TextView = view.statusContent
        val dateContent: TextView = view.dateContent
        val priorityContent: TextView = view.priorityContent

    }
}
