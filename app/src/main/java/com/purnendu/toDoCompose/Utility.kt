package com.purnendu.toDoCompose

import java.util.regex.Matcher
import java.util.regex.Pattern

 fun validateName(name: String): Boolean = name.length < 20


 fun validatePhoneNumber(mobileNo:String): Boolean {
    if (mobileNo.matches(Regex("[0-9]+"))) {
        if (mobileNo.length == 10)
            return true
    }
    return false
}

 fun validateEmail(email:String): Boolean {
    val matcher: Matcher =
        Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE)
            .matcher(email)
    return matcher.find()
}

 fun validatePassword(password:String,confirmPassword:String): Boolean = password == confirmPassword