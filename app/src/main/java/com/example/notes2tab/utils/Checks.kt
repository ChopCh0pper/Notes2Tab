package com.example.notes2tab.utils

import android.util.Log
import android.widget.EditText

fun EditText.isEmailValid(): Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(this.text.toString()).matches()
}

fun checkEmailExists(email: String, callback: (Boolean) -> Unit) {
    AUTH.fetchSignInMethodsForEmail(email).addOnCompleteListener { task ->
        if (task.isSuccessful) {
            val signInMethods = task.result?.signInMethods
            val exists = !signInMethods.isNullOrEmpty()
            callback(exists)
        }
    }
}