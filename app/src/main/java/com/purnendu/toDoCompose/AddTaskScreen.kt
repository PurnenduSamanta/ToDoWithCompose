package com.purnendu.toDoCompose

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun AddTaskScreen(navController: NavController,listIndex:Int=-1) {


    Column(modifier = Modifier.fillMaxSize()) {

        TopAppBar(
            modifier = Modifier.fillMaxWidth(),
            title = { Text(text = "ToDo") },
            backgroundColor = Color.Red,
            contentColor = Color.White
        )

        Column(
            modifier = Modifier
                .padding(horizontal = 30.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {

            val task=(if (listIndex==-1) "" else listOfTask[listIndex].task)
            val dueDate=(if (listIndex==-1) "" else listOfTask[listIndex].dueDate)
            var desc by remember { mutableStateOf(task) }
            var date by remember { mutableStateOf(dueDate) }

            Text(
                text = "Add Your Task",
                fontSize = 30.sp,
            )
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = desc,
                onValueChange = { desc = it },
                placeholder = { Text(text = "Task") }
            )
            Spacer(modifier = Modifier.height(5.dp))
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = date,
                onValueChange = { date = it },
                placeholder = { Text(text = "Due date") },
            )
            Spacer(modifier = Modifier.height(5.dp))
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    if(listIndex==-1)
                    {
                        if (desc.isNotEmpty() && date.isNotEmpty()) {
                            listOfTask.add(Task(desc, date))
                        }
                    }
                    else
                    {
                        if (desc.isNotEmpty() && date.isNotEmpty()) {
                            listOfTask[listIndex].task = desc
                            listOfTask[listIndex].dueDate = date
                        }
                    }
                    navController.navigateUp()
                }
            ) {
                Text(if(listIndex==-1) "Add" else "Update")
            }


        }

    }


}