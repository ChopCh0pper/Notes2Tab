package com.example.notes2tab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var btNav: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btNav = findViewById<BottomNavigationView>(R.id.btNavigation)
        navController = findNavController(R.id.nav_host_fragment)

        //Слушатель выбранного элемента на BottomNavigationView
        btNav.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.btNavHome -> {
                    navController.navigate(R.id.homeFragment)
                    true
                }
                R.id.btNavSearch -> {
                    navController.navigate(R.id.searchFragment)
                    true
                }
                R.id.btNavN2T -> {
                    navController.navigate(R.id.CameraFragment)
                    true
                }
                R.id.btNavSettinds -> {
                    navController.navigate(R.id.settingsFragment)
                    true
                }
                R.id.btNavProfile -> {
                    navController.navigate(R.id.profileFragment)
                    true
                }
                else -> false
            }
        }
    }
}