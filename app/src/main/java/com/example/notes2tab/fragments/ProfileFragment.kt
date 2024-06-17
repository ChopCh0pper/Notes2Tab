package com.example.notes2tab.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.notes2tab.R
import com.example.notes2tab.databinding.FragmentProfileBinding
import com.example.notes2tab.databinding.FragmentTabsBinding
import com.example.notes2tab.utils.AUTH

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private lateinit var navController: NavController

    override fun onStart() {
        super.onStart()
        // Проверяем входил ли юзер до этого, если да, то перекидываем на фрагмент UserFragment
        val currentUser = AUTH.currentUser
        if (currentUser != null && currentUser.isEmailVerified) { navController.navigate(R.id.action_profileFragment_to_userFragment) }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()

        binding.btLogIn.setOnClickListener { navController.navigate(R.id.action_profileFragment_to_logInFragment) }
        binding.btSignUp.setOnClickListener { navController.navigate(R.id.action_profileFragment_to_signUpFragment) }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        fun newInstance() = ProfileFragment()
    }
}