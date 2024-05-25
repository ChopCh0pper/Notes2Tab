package com.example.notes2tab

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isInvisible
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
        val iconColorStates = ContextCompat.getColorStateList(this, R.color.colors_list_state)
        btNav.itemIconTintList = iconColorStates

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
                    createAlert()

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
    private fun createAlert(){
        val builder = AlertDialog.Builder(this)
        builder.setMessage((R.string.choose_method_for_conversion))
        builder.setPositiveButton("Camera") { dialog, which ->
            navController.navigate(R.id.cameraFragment)
            btNav.isInvisible = true
        }

        builder.setNeutralButton("Gallery"){ dialog, which ->
            btNav.isInvisible = true
            navController.navigate(R.id.galleryFragment)
        }

        builder.show()
    }
}