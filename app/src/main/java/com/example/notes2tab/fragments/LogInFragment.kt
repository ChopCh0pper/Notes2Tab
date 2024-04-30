package com.example.notes2tab.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.notes2tab.R
import com.example.notes2tab.databinding.FragmentLogInBinding
import com.example.notes2tab.utils.AUTH
import com.example.notes2tab.utils.invalidityMessage
import com.example.notes2tab.utils.isEmailValid

class LogInFragment : Fragment() {
    private lateinit var binding: FragmentLogInBinding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()

        btLogIn.setOnClickListener {
            if (etEmail.isEmailValid() && etPass.text.isNotEmpty()) {
                logIn(etEmail.text.toString(), etPass.text.toString())
            } else {
                invalidityMessage(etEmail, etPass, requireContext())
            }
        }
    }

    private fun logIn(email: String, pass: String) {
        AUTH.signInWithEmailAndPassword(email, pass)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    navController.navigate(R.id.action_logInFragment_to_userFragment)
                } else {
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.toast_msg_login_failed),
                        Toast.LENGTH_SHORT,
                    ).show()
                }
            }
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