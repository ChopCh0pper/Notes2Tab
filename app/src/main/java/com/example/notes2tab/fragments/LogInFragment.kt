package com.example.notes2tab.fragments

import android.content.Context
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.notes2tab.R
import com.example.notes2tab.dataModels.User
import com.example.notes2tab.dataModels.UserViewModel
import com.example.notes2tab.databinding.FragmentLogInBinding
import com.example.notes2tab.utils.AUTH
import com.example.notes2tab.utils.initUser
import com.example.notes2tab.utils.invalidityMessage
import com.example.notes2tab.utils.isEmailValid
import com.google.firebase.auth.FirebaseUser

class LogInFragment : Fragment() {
    private lateinit var binding: FragmentLogInBinding
    private lateinit var navController: NavController
    private val userViewModel: UserViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()

        btLogIn.setOnClickListener {
            if (etEmail.isEmailValid() && etPass.text.isNotEmpty()) {
                logIn(etEmail.text.toString().trim(), etPass.text.toString()) { user ->
                    if (user.isEmailVerified) {
                        initUser()
                        //после инита сохраняем измененные данные
                        val updatedUser = User(
                            uid = user.uid,
                            email = user.email ?: "",
                            username = user.displayName ?: ""
                        )
                        //обновляем
                        userViewModel.updateUser(updatedUser)
                        navController.navigate(R.id.action_logInFragment_to_userFragment)
                    } else {
                        Toast.makeText(requireContext(),
                            getText(R.string.confirm_email),
                            Toast.LENGTH_SHORT).show()
                        AUTH.signOut()
                    }
                }
            } else {
                invalidityMessage(etEmail, etPass, requireContext())
            }
        }

        tvForgotPass.setOnClickListener {
            showResetPasswordAlert(requireContext())
        }
    }

    private fun logIn(email: String, pass: String, callBack: (FirebaseUser) -> Unit) {
        AUTH.signInWithEmailAndPassword(email, pass)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val currentUser = AUTH.currentUser
                    callBack(currentUser!!)
                } else {
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.toast_msg_login_failed),
                        Toast.LENGTH_SHORT,
                    ).show()
                }
            }
    }
    private fun sendPasswordResetEmail(email: String) {
        AUTH.sendPasswordResetEmail(email).addOnCompleteListener {
            if (it.isSuccessful) {
                Toast.makeText(
                    context,
                    getString(R.string.toast_msg_sending_letter_successfully),
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(
                    context,
                    getString(R.string.toast_msg_change_name_field),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun showResetPasswordAlert(context: Context) {

        val inputEmail = EditText(context).apply {
            hint = getString(R.string.et_hint_email)
            inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
        }

        AlertDialog.Builder(context).apply {
            setTitle(R.string.alert_title_pass_reset)
            setView(inputEmail)
            setPositiveButton(R.string.send) { _, _ ->
                val email = inputEmail.text.toString().trim()
                sendPasswordResetEmail(email)
            }
            setNegativeButton(R.string.cancel, null)
        }.create().show()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLogInBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        fun newInstance() = LogInFragment()
    }
}