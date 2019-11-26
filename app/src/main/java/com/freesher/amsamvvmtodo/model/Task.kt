package com.freesher.amsamvvmtodo.model


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task_table")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int?=null,
    var title: String,
    var description: String,
    val priority: Int,
    val status: String = "todo",
    var date:String

){

}
