package com.example.cocktailmenu.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.cocktailmenu.AlcoholFragment
import com.example.cocktailmenu.NonAlcoholFragment

class ViewPagerAdapter(fm: FragmentManager, private var tabCount: Int) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> AlcoholFragment()
            1 -> NonAlcoholFragment()
            else -> AlcoholFragment()
        }
    }

    override fun getCount(): Int {
        return tabCount
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "Alcohol"
            1 -> "Soft"
            else -> "Alcohol"
        }
    }
}
