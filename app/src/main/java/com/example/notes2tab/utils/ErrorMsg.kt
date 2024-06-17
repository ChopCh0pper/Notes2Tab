package com.example.notes2tab.utils

import android.content.Context
import android.content.res.ColorStateList
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import com.example.notes2tab.R

fun invalidityMessage(etEmail: EditText, etPass: EditText, context: Context) {
    etEmail.backgroundTintList = ColorStateList
        .valueOf(ContextCompat.getColor(context, R.color.errorColor))
    etPass.backgroundTintList = ColorStateList
        .valueOf(ContextCompat.getColor(context, R.color.errorColor))
    Toast.makeText(
        context,
        R.string.toast_msg_validityCheck,
        Toast.LENGTH_SHORT
    ).show()
}

fun existenceOfMailMessage(existence: Boolean, etEmail: EditText, context: Context) {
    etEmail.backgroundTintList = ColorStateList
        .valueOf(ContextCompat.getColor(context, R.color.errorColor))
    when (existence) {
        true -> {
            Toast.makeText(
                context,
                R.string.toast_msg_existence_of_email_true,
                Toast.LENGTH_SHORT
            ).show()
        }

        false -> {
            Toast.makeText(
                context,
                R.string.toast_msg_existence_of_email_false,
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}