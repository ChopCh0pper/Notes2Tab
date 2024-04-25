package com.example.notes2tab.fragments

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.notes2tab.R
import com.example.notes2tab.databinding.FragmentPreviewPhotoBinding

class PreviewPhotoFragment : Fragment() {
    private lateinit var binding: FragmentPreviewPhotoBinding
    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        // Получение переданного URI сделанной фотографии
        val photoUri = arguments?.getParcelable<Uri>("photoUri")

        binding.imageView.setImageURI(photoUri)

        binding.btUsePhoto.setOnClickListener {
            // Надо реализовать отправку в нейросеть...
            navController.navigate(R.id.action_previewPhotoFragment_to_tabsFragment)
        }

        binding.btReshoot.setOnClickListener { navController.popBackStack() }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPreviewPhotoBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        fun newInstance() = PreviewPhotoFragment()
    }
}