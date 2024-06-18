package com.example.notes2tab.utils

import android.util.Log
import android.widget.EditText

fun EditText.isEmailValid(): Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(this.text.toString()).matches()
}