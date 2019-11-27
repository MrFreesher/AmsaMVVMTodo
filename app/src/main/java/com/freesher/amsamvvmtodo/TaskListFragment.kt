package com.freesher.amsamvvmtodo


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.freesher.amsamvvmtodo.adapter.TaskAdapter
import com.freesher.amsamvvmtodo.database.TaskRepository
import com.freesher.amsamvvmtodo.model.Task
import com.freesher.amsamvvmtodo.viewmodel.TaskViewModel
import com.freesher.amsamvvmtodo.viewmodel.TaskViewModelFactory
import kotlinx.android.synthetic.main.fragment_task_list.*

/**
 * A simple [Fragment] subclass.
 */
class TaskListFragment : Fragment() {
    private lateinit var taskViewModelFactory: TaskViewModelFactory
    private lateinit var taskViewModel: TaskViewModel
    private lateinit var database: TaskRepository
    lateinit var adapter: TaskAdapter
    lateinit var linearLayoutManager: LinearLayoutManager
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_task_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // val taskId = arguments?.getInt("id")
        database = TaskRepository(activity!!.application)
        taskViewModelFactory = TaskViewModelFactory(database,activity!!.application)
        taskViewModel = ViewModelProviders.of(this,taskViewModelFactory).get(TaskViewModel::class.java)
        linearLayoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        adapter = TaskAdapter(requireContext(), taskViewModel)
        taskRecyclerView.adapter = adapter
        taskRecyclerView.layoutManager = linearLayoutManager
        taskViewModel.allTasks.observe(viewLifecycleOwner, Observer {tasks->
            tasks?.let { adapter.setTasks(tasks) }
        })


    }


}
