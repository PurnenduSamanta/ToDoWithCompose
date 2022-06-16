package com.purnendu.toDoCompose

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.purnendu.toDoCompose.viewmodel.LoginScreenViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LoginScreen(navController: NavController,viewModel: LoginScreenViewModel= LoginScreenViewModel()) {

    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
    )
    val coroutineScope = rememberCoroutineScope()

    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        sheetContent = {
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            ) {
                BottomSheetContent(navController, viewModel = viewModel)
            }
        }, sheetPeekHeight = 0.dp
    ) {
        ContentLogin(
            navController = navController,
            coroutineScope = coroutineScope,
            bottomSheetScaffoldState = bottomSheetScaffoldState,
            viewModel = viewModel
        )
    }


}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ContentLogin(
    navController: NavController,
    coroutineScope: CoroutineScope,
    bottomSheetScaffoldState: BottomSheetScaffoldState,
    viewModel: LoginScreenViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Log In",
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
        Text(
            text = "Want to log in!",
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
            text = viewModel.mobileNo,
            labelContent = "Mobile No",
            isError = viewModel.isMobileNumberError,
            errorMessage = viewModel.mobileNoErrorMessage,
            keyboardType = KeyboardType.Phone
        )
        {
            viewModel.mobileNo = it
        }

        Text(
            modifier = Modifier
                .align(Alignment.End)
                .clickable {
                    navController.navigate("signUp_screen")
                },
            text = "Don't have account,SignUp here"
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xff74b9ff)),
            enabled = !bottomSheetScaffoldState.bottomSheetState.isExpanded,
            onClick = {

                if (!viewModel.validateMobileNo())
                    return@Button

                coroutineScope.launch {

                    if (bottomSheetScaffoldState.bottomSheetState.isCollapsed) {
                        bottomSheetScaffoldState.bottomSheetState.expand()
                    }
                }
            }) {

            Text(
                modifier = Modifier.padding(5.dp), text = "Log In"
            )

        }
    }

}

@Composable
fun BottomSheetContent(navController: NavController,viewModel: LoginScreenViewModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 2.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val context = LocalContext.current
        Text(text = "Enter your OTP here")
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            SingleOTPField(modifier = Modifier.weight(1f), otp = viewModel.otp1)
            {
                viewModel.otp1 = it
            }
            Spacer(modifier = Modifier.width(2.dp))
            SingleOTPField(modifier = Modifier.weight(1f), otp = viewModel.otp2)
            {
                viewModel. otp2 = it
            }
            Spacer(modifier = Modifier.width(2.dp))
            SingleOTPField(modifier = Modifier.weight(1f), otp = viewModel.otp3)
            {
                viewModel. otp3 = it
            }
            Spacer(modifier = Modifier.width(2.dp))
            SingleOTPField(modifier = Modifier.weight(1f), otp = viewModel.otp4)
            {
                viewModel.otp4 = it
            }
        }
        Spacer(modifier = Modifier.width(5.dp))
        Button(
            onClick = {
                if(!viewModel.validateOTP())
                {
                    Toast.makeText(context, "OTP required", Toast.LENGTH_SHORT).show()
                    return@Button
                }
                navController.navigate("home_screen") {
                    popUpTo("logIn_screen") {
                        inclusive = true
                    }
                }

            },
            modifier = Modifier.fillMaxWidth(0.8f),
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xff74b9ff)),
        ) {
            Text(text = "Submit")
        }


    }

}


@Composable
fun SingleOTPField(modifier: Modifier = Modifier, otp: String, onValueChange: (String) -> Unit) {

    OutlinedTextField(
        modifier = modifier,
        singleLine = true,
        textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
        value = otp, onValueChange = { if (it.length <= 1) onValueChange(it) },
        shape = RoundedCornerShape(3.dp),
        colors = TextFieldDefaults.textFieldColors(
            focusedLabelColor = Color.Black,
            unfocusedIndicatorColor = Color.Gray,
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
    )


}