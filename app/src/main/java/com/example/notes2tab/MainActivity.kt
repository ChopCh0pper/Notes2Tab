package com.example.notes2tab

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.notes2tab.utils.initFirebase
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var btNav: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initFirebase()



        btNav = findViewById<BottomNavigationView>(R.id.btNavigation)
        navController = findNavController(R.id.nav_host_fragment)
        val iconColorStates = ContextCompat.getColorStateList(this, R.color.colors_list_state)
        btNav.itemIconTintList = iconColorStates

        if (this.requestedOrientation == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE){
            btNav.visibility = View.GONE
        }

        // Слушатель выбранного элемента на BottomNavigationView
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

    private fun createAlert() {
        val builder = AlertDialog.Builder(this)
        builder.setMessage((R.string.choose_method_for_conversion))
        builder.setPositiveButton("Camera") { dialog, which ->

            btNav.visibility = View.GONE
            navController.navigate(R.id.cameraFragment)
        }

        builder.setNeutralButton("Gallery") { dialog, which ->

            btNav.visibility = View.GONE
            navController.navigate(R.id.galleryFragment)
        }

        builder.show()
    }


}
