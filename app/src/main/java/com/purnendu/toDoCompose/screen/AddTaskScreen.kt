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
import com.purnendu.toDoCompose.viewmodel.AddTaskScreenViewModel

@Composable
fun AddTaskScreen(navController: NavController, viewModel: AddTaskScreenViewModel) {


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
            Text(
                text = "Add Your Task",
                fontSize = 30.sp,
            )
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = viewModel.desc,
                onValueChange = { viewModel.desc = it },
                placeholder = { Text(text = "Task") }
            )
            Spacer(modifier = Modifier.height(5.dp))
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = viewModel.date,
                onValueChange = { viewModel.date = it },
                placeholder = { Text(text = "Due date") },
            )
            Spacer(modifier = Modifier.height(5.dp))
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    viewModel.addTask()
                    navController.navigateUp()
                }
            ) {
                Text(if (viewModel.index == -1) "Add" else "Update")
            }


        }

    }


}