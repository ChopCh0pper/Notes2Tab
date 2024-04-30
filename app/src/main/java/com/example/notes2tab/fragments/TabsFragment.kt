package com.example.notes2tab.fragments

import TabDrawer
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
        rootView = binding.root // Assign rootView here
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Вызов метода отрисовки табулатуры после создания представления
        drawTab()
    }

    private fun drawTab() {
        // Получение данных для отрисовки табулатуры
        val tabData = stringTabParser.parseFile("kuzne4ik")

        tabDrawer.setDigitSize(80, 80)

        // Отрисовка табулатуры и получение Bitmap
        val bitmap = tabDrawer.drawTab(tabData, 0, 0)

        // Создание ImageView и установка Bitmap в него
        val imageView = ImageView(requireContext())
        imageView.setImageBitmap(bitmap)

        // Добавление ImageView в FrameLayout
        rootView.findViewById<FrameLayout>(R.id.container)?.addView(imageView)
    }

    companion object {
        fun newInstance(param1: String, param2: String) = TabsFragment()
    }
}