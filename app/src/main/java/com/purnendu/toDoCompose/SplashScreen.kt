package com.purnendu.toDoCompose


import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.purnendu.toDoCompose.ui.theme.ToDoTheme
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {

   val scale =remember{
       Animatable(initialValue = 0f)
   }
    LaunchedEffect(key1 = true)
    {
       scale.animateTo(targetValue = 3f, animationSpec = tween(
           durationMillis = 1000,
           easing = LinearEasing
       ))

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
            modifier = Modifier.scale(scale.value),
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