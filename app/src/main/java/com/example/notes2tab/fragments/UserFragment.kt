package com.example.notes2tab.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.notes2tab.R
import com.example.notes2tab.databinding.FragmentUserBinding
import com.example.notes2tab.utils.AUTH
import com.google.firebase.auth.FirebaseUser

class UserFragment : Fragment() {
    private lateinit var binding: FragmentUserBinding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        val currentUser = AUTH.currentUser
        tvUserName.text = currentUser!!.email
        btSignOut.setOnClickListener { signOut() }
    }

    private fun signOut() {
        AUTH.signOut()
        navController.navigate(R.id.action_userFragment_to_profileFragment)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        fun newInstance() = UserFragment()
    }
}