package com.freesher.amsamvvmtodo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceFragment("show")
    }

    fun replaceFragment(action:String,id:Int=0){
        val transaction = supportFragmentManager.beginTransaction()
        when(action){
            "show"->{
                transaction.replace(R.id.fragment_container,TaskListFragment()).addToBackStack(null)
            }
            "edit"->{
                val bundle = Bundle()
                bundle.putInt("id",id)
                val editFragment = EditTaskFragment()
                editFragment.arguments = bundle
                transaction.replace(R.id.fragment_container,editFragment).addToBackStack(null)
            }
        }
        transaction.commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = MenuInflater(applicationContext).inflate(R.menu.toolbar_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.addTask->{
                val intent = Intent(this,AddTaskActivity::class.java)
                startActivity(intent)
            }
        }
        return true
    }
}
