package com.example.notes2tab.fragments

import android.app.AlertDialog
import android.content.Context
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.notes2tab.R
import com.example.notes2tab.dataModels.User
import com.example.notes2tab.databinding.FragmentSignUpBinding
import com.example.notes2tab.utils.*


class SignUpFragment : Fragment() {
    private lateinit var binding: FragmentSignUpBinding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()

        btSignUp.setOnClickListener {
            if (etEmail.isEmailValid() && etPass.text.isNotEmpty() && etUserName.text.isNotEmpty()) {
                        createAccount(
                            etEmail.text.toString().trim(),
                            etUserName.text.toString().trim(),
                            etPass.text.toString())
            } else { invalidityMessage(etUserName, etEmail, etPass, requireContext()) }
        }
    }

    private fun createAccount(email: String, username: String, pass: String) {
        AUTH.createUserWithEmailAndPassword(email, pass)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val currentUser = AUTH.currentUser!!
                    currentUser.sendEmailVerification()
                    initializeUserInFirestore(currentUser.uid, username, email)
                    createDialog()
                    navController.navigate(R.id.action_signUpFragment_to_logInFragment)
                } else {
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.toast_msg_signup_failed),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    //Добавление нового юзера в БД
    private fun initializeUserInFirestore(uid: String, username: String, email: String) {
        val user = User(uid, username, email)
        DATABASE.collection(COLLECTION_USERS).document(uid)
            .set(user)
    }

    //уведомление об успешной регистрации и о требовании подтвердить почту
    private fun createDialog() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle(getText(R.string.toast_msg_signup_successfully))
            .setMessage(getText(R.string.confirm_email))
            .setPositiveButton(getText(R.string.ok)) {
                    dialog, _ ->  dialog.cancel()
            }
        val alertDialog = builder.create()
        alertDialog.show()
    }

    private fun invalidityMessage(etUserName: EditText, etEmail: EditText, etPass: EditText, context: Context) {
        etUserName.backgroundTintList = ColorStateList
            .valueOf(ContextCompat.getColor(context, R.color.errorColor))
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        fun newInstance() = SignUpFragment()
    }
}