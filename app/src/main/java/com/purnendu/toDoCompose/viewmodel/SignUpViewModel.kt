package com.purnendu.toDoCompose.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.purnendu.toDoCompose.utility.validateEmail
import com.purnendu.toDoCompose.utility.validateName
import com.purnendu.toDoCompose.utility.validatePassword
import com.purnendu.toDoCompose.utility.validatePhoneNumber

class SignUpViewModel : ViewModel() {

    var firstName by mutableStateOf("")
    var lastName by mutableStateOf("")
    var mobileNo by mutableStateOf("")
    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var confirmPassword by mutableStateOf("")

    var isFirstNameError by mutableStateOf(false)
    var isLastNameError by mutableStateOf(false)
    var isMobileNumberError by mutableStateOf(false)
    var isEmailError by mutableStateOf(false)
    var isPasswordError by mutableStateOf(false)
    var isConfirmPasswordError by mutableStateOf(false)

    var firstNameErrorMessage by mutableStateOf("")
    var lastNameErrorMessage by mutableStateOf("")
    var mobileNoErrorMessage by mutableStateOf("")
    var emailErrorMessage by mutableStateOf("")
    var passwordErrorMessage by mutableStateOf("")
    var confirmPasswordErrorMessage by mutableStateOf("")


    fun validateFirstName(): Boolean {
        if (firstName.isEmpty()) {
            isFirstNameError = true
            firstNameErrorMessage = "Required Field"
            return false
        }
        if (!validateName(firstName)) {
            isFirstNameError = true
            firstNameErrorMessage = "More than 20 letters not allowed"
            return false

        }
        isFirstNameError = false
        return true
    }

    fun validateLastName(): Boolean {
        if (lastName.isEmpty()) {
            isLastNameError = true
            lastNameErrorMessage = "Required Field"
            return false
        }
        if (!validateName(lastName)) {
            isLastNameError = true
            lastNameErrorMessage = "More than 20 letters not allowed"
            return false
        }
        isLastNameError = false
        return true
    }

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

    fun validateEmail(): Boolean {
        if (email.isEmpty()) {
            isEmailError = true
            emailErrorMessage = "Required Field"
            return false
        }

        if (!validateEmail(email)) {
            isEmailError = true
            emailErrorMessage = "Not a valid Email"
            return false
        }
        isEmailError = false
        return true
    }

    fun validatePassword(): Boolean {
        if (password.isEmpty()) {
            isPasswordError = true
            passwordErrorMessage = "Required Field"
            return false
        }

        if (password.length < 10) {
            isPasswordError = true
            passwordErrorMessage = "Password should be at least 10 characters long "
            return false
        }
        isPasswordError = false
        return true
    }

    fun validateConfirmPassword(): Boolean {
        if (confirmPassword.isEmpty()) {
            isConfirmPasswordError = true
            confirmPasswordErrorMessage = "Required Field"
            return false
        }

        if (!validatePassword(password, confirmPassword)) {
            isConfirmPasswordError = true
            confirmPasswordErrorMessage = "Password not matching"
            return false
        }
        isConfirmPasswordError = false
        return true
    }

}