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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

private var mobileNo by mutableStateOf("")
private var isMobileNumberError by mutableStateOf(false)
private var mobileNoErrorMessage by mutableStateOf("")

private var otp1 by mutableStateOf("")
private var otp2 by mutableStateOf("")
private var otp3 by mutableStateOf("")
private var otp4 by mutableStateOf("")

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LoginScreen(navController: NavController) {

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
                BottomSheetContent(navController)
            }
        }, sheetPeekHeight = 0.dp
    ) {
        ContentLogin(
            navController = navController,
            coroutineScope = coroutineScope,
            bottomSheetScaffoldState = bottomSheetScaffoldState
        )
    }


}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ContentLogin(
    navController: NavController,
    coroutineScope: CoroutineScope,
    bottomSheetScaffoldState: BottomSheetScaffoldState
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
            text = mobileNo,
            labelContent = "Mobile No",
            isError = isMobileNumberError,
            errorMessage = mobileNoErrorMessage,
            keyboardType = KeyboardType.Phone
        )
        {
            mobileNo = it
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
                if (mobileNo.isEmpty()) {
                    isMobileNumberError = true
                    mobileNoErrorMessage = "Required Field"
                    return@Button
                }
                if (!validatePhoneNumber(mobileNo)) {
                    isMobileNumberError = true
                    mobileNoErrorMessage = "Not a valid Mobile Number"
                    return@Button
                }
                isMobileNumberError = false

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
fun BottomSheetContent(navController: NavController) {
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
            SingleOTPField(modifier = Modifier.weight(1f), otp = otp1)
            {
                otp1 = it
            }
            Spacer(modifier = Modifier.width(2.dp))
            SingleOTPField(modifier = Modifier.weight(1f), otp = otp2)
            {
                otp2 = it
            }
            Spacer(modifier = Modifier.width(2.dp))
            SingleOTPField(modifier = Modifier.weight(1f), otp = otp3)
            {
                otp3 = it
            }
            Spacer(modifier = Modifier.width(2.dp))
            SingleOTPField(modifier = Modifier.weight(1f), otp = otp4)
            {
                otp4 = it
            }
        }
        Spacer(modifier = Modifier.width(5.dp))
        Button(
            onClick = {
                if (otp1.isEmpty()) {
                    Toast.makeText(context, "Enter OTP", Toast.LENGTH_SHORT).show()
                    return@Button
                }
                if (otp2.isEmpty()) {
                    Toast.makeText(context, "Enter OTP", Toast.LENGTH_SHORT).show()
                    return@Button
                }
                if (otp3.isEmpty()) {
                    Toast.makeText(context, "Enter OTP", Toast.LENGTH_SHORT).show()
                    return@Button
                }
                if (otp4.isEmpty()) {
                    Toast.makeText(context, "Enter OTP", Toast.LENGTH_SHORT).show()
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