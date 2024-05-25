package com.example.notes2tab.fragments

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.provider.MediaStore.Audio.Media
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.notes2tab.R
class GalleryFragment : Fragment() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        navController = findNavController()
        openGallery()
        navController.navigate(R.id.tabsFragment)
        return inflater.inflate(R.layout.fragment_gallery, container, false)
    }

    companion object {
        fun newInstance() = GalleryFragment()
    }

    private fun openGallery(){
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivity(intent)
    }
}