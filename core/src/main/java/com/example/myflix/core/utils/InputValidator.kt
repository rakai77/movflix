package com.example.myflix.core.utils

object InputValidator {

    fun getEmailErrorOrNull(input: String): String? {
        return when {
            !input.isValidEmail() -> "Invalid email format"
            else -> null
        }
    }

    fun getPasswordErrorOrNull(input: String): String? {
        return when {
            !input.isValidPassword() -> "Password should have minimum eight characters, at least one letter, one number and one special character"
            else -> null
        }
    }

}