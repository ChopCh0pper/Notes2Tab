package com.example.notes2tab.fragments

import TabDrawer
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.notes2tab.R
import com.example.notes2tab.databinding.FragmentTabsBinding
import com.example.notes2tab.drawTabs.StringTabParser
import com.google.android.material.bottomnavigation.BottomNavigationView

class TabsFragment : Fragment() {
    private lateinit var binding: FragmentTabsBinding
    private lateinit var stringTabParser: StringTabParser
    private lateinit var tabDrawer: TabDrawer
    private lateinit var rootView: View
    private lateinit var navController: NavController
    private var btNav: BottomNavigationView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        stringTabParser = StringTabParser(requireContext())
        tabDrawer = TabDrawer(requireContext())

        // Установка флага для использования меню в фрагменте
        setHasOptionsMenu(true)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTabsBinding.inflate(inflater, container, false)
        rootView = binding.root

        // Initialize BottomNavigationView
        btNav = requireActivity().findViewById(R.id.btNavigation)

        // Set screen orientation
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        btNav?.visibility = View.GONE
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = findNavController()

        rootView.findViewById<ImageView>(R.id.back_button)?.setOnClickListener {
            onBackButtonClick()
        }

        rootView.findViewById<ImageView>(R.id.settings)?.setOnClickListener {
            onSettingsButtonClick()
        }

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

    private fun onBackButtonClick() {
        navController.navigate(R.id.homeFragment)
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        btNav?.visibility = View.VISIBLE // Показать BottomNavigationView при возврате
    }

    private fun onSettingsButtonClick() {
        Toast.makeText(requireContext(), "Эта кнопочка еще не готова)", Toast.LENGTH_SHORT).show()
    }
}
