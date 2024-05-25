package com.example.notes2tab.fragments

import TabDrawer
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.notes2tab.R
import com.example.notes2tab.databinding.FragmentTabsBinding
import com.example.notes2tab.drawTabs.StringTabParser

class TabsFragment : Fragment() {
    private lateinit var binding: FragmentTabsBinding
    private lateinit var stringTabParser : StringTabParser
    private lateinit var tabDrawer: TabDrawer
    private lateinit var rootView: View



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //путь в raw файлах, для заглушки
        stringTabParser = StringTabParser(requireContext())
        tabDrawer = TabDrawer(requireContext())


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTabsBinding.inflate(inflater, container, false)
        rootView = binding.root
        requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Вызов метода отрисовки табулатуры после создания представления
        drawTab()
    }

    private fun drawTab() {
        val tabData = stringTabParser.parseFile("kuzne4ik")
        val screenWidth = resources.displayMetrics.widthPixels // Получение ширины экрана

        val bitmap = tabDrawer.drawTab(tabData, screenWidth)
        val imageView = ImageView(requireContext())
        imageView.setImageBitmap(bitmap)
        rootView.findViewById<FrameLayout>(R.id.container)?.addView(imageView)
    }

    companion object {
        fun newInstance(param1: String, param2: String) = TabsFragment()
    }
}