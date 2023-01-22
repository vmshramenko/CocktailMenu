package com.example.cocktailmenu.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.example.cocktailmenu.R
import com.example.cocktailmenu.activity.SearchCocktailsActivity
import com.example.cocktailmenu.adapter.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout

class CatalogFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_catalog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val searchView = view.findViewById<View>(R.id.search_cocktails)
        val viewPager = view.findViewById<ViewPager>(R.id.view_pager)
        val tabCocktails = view.findViewById<TabLayout>(R.id.cocktail_tab)
        val adapter = ViewPagerAdapter(parentFragmentManager, tabCocktails.tabCount)
        viewPager.adapter = adapter
        tabCocktails.setupWithViewPager(viewPager)

        searchView.setOnClickListener {
            val intent = Intent(it.context, SearchCocktailsActivity::class.java)
            startActivity(intent)
        }


    }

}