package com.example.notes2tab.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.notes2tab.R
import com.example.notes2tab.dataModels.User
import com.example.notes2tab.dataModels.UserViewModel
import com.example.notes2tab.databinding.FragmentUserBinding
import com.example.notes2tab.utils.AUTH


class UserFragment : Fragment() {
    private lateinit var binding: FragmentUserBinding
    private lateinit var navController: NavController
    private val userViewModel: UserViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        //установка наблюдателя за данными юзеров (ниже объяснение)
        //автоматическое обновление данных при изменении с помощью обсервера (наблюдателя)
        // который следит за изменениями данных в лайвдата
        userViewModel.user.observe(viewLifecycleOwner, Observer { user -> //viewLifecycleOwner обеспечивает
            // корректное управление жизненным циклом наблюдателя чтобы он не продолжал обновлять UI
            binding.tvUserName.text = user.uid //внутри обсервера мы обновляем непосредсьвенно UI
        })

        btSignOut.setOnClickListener { signOut() }
    }

    private fun signOut() {
        AUTH.signOut()
        userViewModel.updateUser(User())
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
//negokab396@devncie.com