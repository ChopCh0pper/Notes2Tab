package com.example.notes2tab.fragments

import android.content.Context
import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.notes2tab.R
import com.example.notes2tab.dataModels.User
import com.example.notes2tab.databinding.FragmentUserBinding
import com.example.notes2tab.utils.AUTH
import com.example.notes2tab.utils.COLLECTION_USERS
import com.example.notes2tab.utils.CURRENT_UID
import com.example.notes2tab.utils.DATABASE
import com.example.notes2tab.utils.USER

class UserFragment : Fragment() {
    private lateinit var binding: FragmentUserBinding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        tvUserName.text = USER.username
        btSignOut.setOnClickListener { signOut() }
        tvDelete.setOnClickListener { showDeleteAccountAlert(requireContext()) }
    }

    private fun showDeleteAccountAlert(context: Context) {

        AlertDialog.Builder(context).apply {
            setTitle(R.string.alert_title_delete_account)
            setMessage(R.string.alert_msg_delete_account)
            setPositiveButton(R.string.yes) { _, _ ->
                navController.navigate(R.id.action_userFragment_to_profileFragment)
            }
            setNegativeButton(R.string.no, null)
        }.create().show()
    }

    private fun signOut() {
        AUTH.signOut()
        USER = User()
        navController.navigate(R.id.action_userFragment_to_profileFragment)
    }

    private fun delete() {
//        AUTH.currentUser?.delete()
//        DATABASE.collection(COLLECTION_USERS).document(CURRENT_UID).delete()
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