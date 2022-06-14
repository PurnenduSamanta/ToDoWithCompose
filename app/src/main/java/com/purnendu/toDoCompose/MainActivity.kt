package com.purnendu.toDoCompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.purnendu.toDoCompose.ui.theme.ToDoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToDoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Navigation()
                }
            }
        }
    }

    @Composable
    fun Navigation() {

        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "splash_screen")
        {
            composable(route = "splash_screen")
            {
                SplashScreen(navController = navController)
            }
            composable(route = "logIn_screen")
            {
                LoginScreen(navController = navController)
            }
            composable(route = "signUp_screen")
            {
                SignUpScreen(navController = navController)
            }

            composable(route = "home_screen")
            {
                HomeScreen(navController = navController)
            }
            composable(route = "add_task_page?index={index}",
                arguments = listOf(
                    navArgument("index")
                    {
                        type = NavType.IntType
                        defaultValue = -1
                        nullable = false
                    }
                )
            )
            {
                it.arguments?.let { it1 ->
                   AddTaskScreen(
                        navController = navController,
                        listIndex = it1.getInt("index")
                    )
                }
            }
        }

    }
}

