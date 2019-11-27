package com.freesher.amsamvvmtodo.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Entity(tableName = "task_table")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int?=null,
    var title: String,
    var description: String,
    val priority: Int,
    var status: String = "todo",
    var date:String


){

}
