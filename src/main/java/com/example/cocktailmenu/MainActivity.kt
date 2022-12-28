package com.example.cocktailmenu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNav.setOnItemSelectedListener(navListener)

        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, AlcoholFragment()).commit()
    }
    private val navListener = BottomNavigationView.OnNavigationItemSelectedListener {
        lateinit var selectedFragment: Fragment
        when (it.itemId) {
            R.id.alcohol -> {
                selectedFragment = AlcoholFragment()
                true

            }
            R.id.nonAlcohol -> {
                selectedFragment = NonAlcoholFragment()
                true
            }
        }
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, selectedFragment).commit()
        true
    }
}