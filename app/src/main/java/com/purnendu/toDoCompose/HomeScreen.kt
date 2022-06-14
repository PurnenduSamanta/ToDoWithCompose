package com.purnendu.toDoCompose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController) {

    Column(modifier = Modifier.fillMaxSize()) {

        TopAppBar(
            modifier = Modifier.fillMaxWidth(),
            title = { Text(text = "ToDo") },
            backgroundColor = Color.Red,
            contentColor = Color.White
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            GridLayout(modifier = Modifier.padding(10.dp)) {
                navController.navigate("add_task_page?index=$it")
            }

            FloatingActionButton(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(end = 10.dp, bottom = 10.dp)
                    .size(50.dp),
                onClick = {
                    navController.navigate("add_task_page")
                }) {

                Icon(Icons.Filled.Add, "")

            }

        }
    }




}

@Composable
fun GridLayout(
    modifier: Modifier = Modifier,
    fn: (index: Int) -> Unit
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(2),
    )
    {

        items(listOfTask.size)
        {
            SingleTask(
                taskDesc = listOfTask[it].task,
                dueDate = listOfTask[it].dueDate,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { fn(it) }
            )

        }
    }
}


@Composable
fun SingleTask(
    modifier: Modifier = Modifier,
    taskDesc: String,
    dueDate: String
) {
    Card(
        modifier = modifier
            .padding(3.dp),
        elevation = 3.dp,
        shape = RoundedCornerShape(3.dp),
        border = BorderStroke(1.dp, color = Color.Green)
    ) {

        Column(
            modifier = Modifier
                .padding(5.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = taskDesc)
            Spacer(modifier = Modifier.height(3.dp))
            Text(text = dueDate)
        }
    }
}