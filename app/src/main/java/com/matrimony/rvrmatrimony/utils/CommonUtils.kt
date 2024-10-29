package com.matrimony.rvrmatrimony.utils

import android.content.Context
import android.util.Patterns
import android.widget.Toast
import java.util.*
import java.util.regex.Pattern

object CommonUtils {
    private val NAME_PATTERN by lazy { "^([A-Za-z0-9_!#-]+ )+[A-Za-z0-9_#!-]+\$|^[A-Za-z0-9_#!.-]+\$" }
    private val EMAIL_PATTERN by lazy { "^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$" }
    private val PASSWORD_PATTERN by lazy {
        "^" + "(?=.*[0-9])" +         //at least 1 digit
                "(?=.*[a-z])" +         //at least 1 lower case letter
                "(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                "(?=.*[@#$%^&+=_])" +    //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{8,}" +               //at least 8 characters
                "$"
    }
    private val PIN_CODE_PATTERN by lazy { "^[1-9]{1}[0-9]{2}\\s{0,1}[0-9]{3}$" }

    fun isValidText(textName: String): Boolean {
        return textName.matches(NAME_PATTERN.toRegex())
    }

    //Email Validation
    fun isValidEmailId(givenId: String): Boolean {
        return givenId.matches(EMAIL_PATTERN.toRegex())
    }

    //Url Validation
    fun isValidUrl(url: String): Boolean {
        return url.matches(Patterns.WEB_URL.toRegex())
    }

    //Phone Number Validation
    fun isValidPhoneNumber(phone: String): Boolean {
        return Patterns.PHONE.matcher(phone).matches()
    }

    //Password Validation
    fun isValidPasswords(password: String): Boolean {
        return password.isNotEmpty() && password.length <= 32
    }

    fun isValidPasswordFormat(password: String): Boolean {
        val passwordREGEX = Pattern.compile(PASSWORD_PATTERN)
        return passwordREGEX.matcher(password).matches()
    }


}