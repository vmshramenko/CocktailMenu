package com.example.cocktailmenu.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.cocktailmenu.*
import com.example.cocktailmenu.fragment.HomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    lateinit var selectedFragment: Fragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_nav)
        bottomNav.setOnItemSelectedListener(navListener)
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, HomeFragment()).commit()
    }

    private val navListener = BottomNavigationView.OnNavigationItemSelectedListener {

        when (it.itemId) {
            R.id.home -> {
                selectedFragment = HomeFragment()
            }
            /*R.id.nonAlcohol -> {
                selectedFragment = NonAlcoholFragment()
            }*/
        }
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, selectedFragment).commit()
        true
    }
}