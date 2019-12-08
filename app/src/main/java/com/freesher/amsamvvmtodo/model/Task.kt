package com.freesher.amsamvvmtodo.model


import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "task_table")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int?=null,
    var title: String ="",
    var description: String,
    val priority: Int,
    var status: String = "todo",
    var date:String
):Parcelable{

}
