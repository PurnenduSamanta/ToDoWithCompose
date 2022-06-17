package com.purnendu.toDoCompose.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.purnendu.toDoCompose.utility.Task
import com.purnendu.toDoCompose.utility.listOfTask

class AddTaskScreenViewModel(val index:Int=-1):ViewModel() {

    private val task=(if (index==-1) "" else listOfTask[index].task)
    private val dueDate=(if (index==-1) "" else listOfTask[index].dueDate)

    var desc by mutableStateOf(task)
    var date by mutableStateOf(dueDate)

    fun addTask()
    {
        if(index==-1)
        {
            if (desc.isNotEmpty() && date.isNotEmpty()) {
                listOfTask.add(Task(desc, date))
            }
        }
        else
        {
            if (desc.isNotEmpty() && date.isNotEmpty()) {
                listOfTask[index].task = desc
                listOfTask[index].dueDate = date
            }
        }
    }

}

