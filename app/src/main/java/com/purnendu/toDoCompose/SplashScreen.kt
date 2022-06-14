package com.purnendu.toDoCompose


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.purnendu.toDoCompose.ui.theme.ToDoTheme
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {

    LaunchedEffect(key1 = true)
    {
        delay(1000L)
        navController.navigate("logIn_screen") {
            popUpTo("splash_screen") {
                inclusive = true
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Image(
            painter = painterResource(id = R.mipmap.ic_launcher),
            modifier = Modifier.fillMaxSize(0.5f),
            contentDescription = "Splash Icon"
        )
    }


}


@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    ToDoTheme {
        // SplashScreen()
    }
}