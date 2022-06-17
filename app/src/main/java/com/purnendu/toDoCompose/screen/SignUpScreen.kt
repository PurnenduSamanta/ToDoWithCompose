package com.purnendu.toDoCompose

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.purnendu.toDoCompose.viewmodel.SignUpViewModel


@Composable
fun SignUpScreen(navController: NavController,viewModel:SignUpViewModel) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {

        val context = LocalContext.current

        Text(
            text = "Sign Up",
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
        Text(
            text = "Want to sign up fill out this form!",
            color = Color.Gray,
            fontSize = 15.sp
        )

        Spacer(modifier = Modifier.height(10.dp))

        Divider(
            color = Color.LightGray,
            modifier = Modifier
                .fillMaxWidth()
                .width(0.5.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        CustomTextField(
            text = viewModel.firstName,
            labelContent = "First Name",
            isError = viewModel.isFirstNameError,
            errorMessage = viewModel.firstNameErrorMessage,
            keyboardType = KeyboardType.Text,
        )
        {
            viewModel.firstName = it
        }

        Spacer(modifier = Modifier.height(10.dp))

        CustomTextField(
            text = viewModel.lastName,
            labelContent = "Last Name",
            isError = viewModel.isLastNameError,
            errorMessage = viewModel.lastNameErrorMessage,
            keyboardType = KeyboardType.Text
        )
        {
            viewModel.lastName = it
        }

        Spacer(modifier = Modifier.height(10.dp))

        CustomTextField(
            text = viewModel.mobileNo,
            labelContent = "Mobile No",
            isError = viewModel.isMobileNumberError,
            errorMessage = viewModel.mobileNoErrorMessage,
            keyboardType = KeyboardType.Phone
        )
        {
            viewModel.mobileNo = it
        }

        Spacer(modifier = Modifier.height(10.dp))

        CustomTextField(
            text = viewModel.email,
            labelContent = "Email",
            isError = viewModel.isEmailError,
            errorMessage = viewModel.emailErrorMessage,
            keyboardType = KeyboardType.Email
        )
        {
            viewModel.email = it
        }

        Spacer(modifier = Modifier.height(10.dp))

        CustomTextField(
            text = viewModel.password,
            labelContent = "Password",
            isError = viewModel.isPasswordError,
            errorMessage = viewModel.passwordErrorMessage,
            keyboardType = KeyboardType.Password
        )
        {
            viewModel.password = it
        }

        Spacer(modifier = Modifier.height(10.dp))

        CustomTextField(
            text = viewModel.confirmPassword,
            labelContent = "Confirm Password",
            isError = viewModel.isConfirmPasswordError,
            errorMessage = viewModel.confirmPasswordErrorMessage,
            keyboardType = KeyboardType.Password
        )
        {
            viewModel.confirmPassword = it
        }

        Spacer(modifier = Modifier.height(20.dp))
        Button(
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xff74b9ff)),
            onClick = {

              if(!viewModel.validateFirstName())
                  return@Button

                if(!viewModel.validateLastName())
                    return@Button

                if(!viewModel.validateMobileNo())
                    return@Button

                if(!viewModel.validateEmail())
                    return@Button

                if(!viewModel.validatePassword())
                    return@Button

                if(!viewModel.validateConfirmPassword())
                    return@Button

                Toast.makeText(context, "Registration Successful", Toast.LENGTH_SHORT)
                    .show()

//                firstName = ""
//                lastName = ""
//                mobileNo = ""
//                email = ""
//                password = ""
//                confirmPassword = ""

                navController.navigate("home_screen") {
                    popUpTo("logIn_screen") {
                        inclusive = true
                    }
                }


            }) {
            Text(
                modifier = Modifier.padding(5.dp), text = "Sign Up"
            )
        }


    }

}

