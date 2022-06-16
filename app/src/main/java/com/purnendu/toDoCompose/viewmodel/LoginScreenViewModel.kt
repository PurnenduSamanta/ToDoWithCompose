package com.purnendu.toDoCompose.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.purnendu.toDoCompose.validatePhoneNumber

class LoginScreenViewModel : ViewModel() {

     var mobileNo by mutableStateOf("")
     var isMobileNumberError by mutableStateOf(false)
     var mobileNoErrorMessage by mutableStateOf("")

    var otp1 by mutableStateOf("")
    var otp2 by mutableStateOf("")
    var otp3 by mutableStateOf("")
    var otp4 by mutableStateOf("")


    fun validateMobileNo(): Boolean {
        if (mobileNo.isEmpty()) {
            isMobileNumberError = true
            mobileNoErrorMessage = "Required Field"
            return false
        }

        if (!validatePhoneNumber(mobileNo)) {
            isMobileNumberError = true
            mobileNoErrorMessage = "Not a valid Mobile Number"
            return false
        }
        isMobileNumberError = false
        return true
    }

    fun validateOTP(): Boolean {
        if (otp1.isEmpty())
            return false
        if (otp2.isEmpty())
            return false
        if (otp3.isEmpty())
            return false
        if (otp4.isEmpty())
            return false

        return true
    }


}