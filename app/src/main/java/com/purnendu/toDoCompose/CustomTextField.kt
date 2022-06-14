package com.purnendu.toDoCompose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun CustomTextField(
    text: String,
    labelContent: String,
    keyboardType: KeyboardType,
    isError: Boolean,
    errorMessage: String,
    onValueChange: (String) -> Unit
) {


    Column {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = text,
            onValueChange = {
                onValueChange(it)
            },
            trailingIcon = {
                if (isError)
                    Icon(
                        painter = painterResource(id = R.drawable.ic_baseline_error_outline_24),
                        "error",
                        tint = MaterialTheme.colors.error
                    )
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = Color.White,
                focusedBorderColor = Color.Blue,
                unfocusedBorderColor = Color.LightGray,
                focusedLabelColor = Color.Black,
                textColor = Color.Gray
            ),
            shape = RoundedCornerShape(5.dp),
            isError = isError,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            label = { Text(text = labelContent) },
            visualTransformation = if (keyboardType == KeyboardType.Password)
                PasswordVisualTransformation()
            else
                VisualTransformation.None
        )
        if (isError) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colors.error,
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(start = 16.dp)
            )
        }

    }

}