package com.example.cocktailmenu.fragment

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.cocktailmenu.*
import com.example.cocktailmenu.activity.CocktailDetailsActivity
import com.example.cocktailmenu.activity.SearchCocktailsActivity

class HomeFragment : Fragment() {
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var adapterAlcohol: ApiCocktailsAdapter
    private lateinit var adapterNonAlcohol: ApiCocktailsAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        val recyclerAlcohol = view.findViewById<RecyclerView>(R.id.recycler_alcohol)
        val recyclerNonAlcohol = view.findViewById<RecyclerView>(R.id.recycler_nonAlcohol)
        val pBarAlcohol = view.findViewById<ProgressBar>(R.id.pBarAlcohol)
        val pBarNonAlcohol = view.findViewById<ProgressBar>(R.id.pBarNonAlcohol)
        val searchView = view.findViewById<View>(R.id.search_cocktails)
        adapterAlcohol = ApiCocktailsAdapter(emptyList(), clickListener =  { id -> launchCocktailDetails(id)})
        adapterNonAlcohol = ApiCocktailsAdapter(emptyList(), clickListener =  { id -> launchCocktailDetails(id)})
        recyclerAlcohol.adapter = adapterAlcohol
        recyclerNonAlcohol.adapter = adapterNonAlcohol

        searchView.setOnClickListener{
            val intent = Intent(it.context, SearchCocktailsActivity::class.java)
            startActivity(intent)
        }
        //val pBar = view.findViewById<ProgressBar>(R.id.progressBar)
        lifecycleScope.launchWhenStarted {
            homeViewModel.cocktail.collect {
                when {
                    !it.cocktails.isNullOrEmpty() -> {
                        adapterAlcohol.setCocktails(it.cocktails)
                        pBarAlcohol.isVisible = false
                    }
                    !it.errorMessage.isNullOrEmpty() -> {
                        Toast.makeText(
                            requireContext(), it.errorMessage,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }
        lifecycleScope.launchWhenStarted {
            homeViewModel.nonAlcoholCocktail.collect{
                when{
                    !it.cocktails.isNullOrEmpty() -> {
                        adapterNonAlcohol.setCocktails(it.cocktails)
                        pBarNonAlcohol.isVisible = false
                    }
                    !it.errorMessage.isNullOrEmpty() -> {
                        Toast.makeText(
                            requireContext(), it.errorMessage,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }
    }
    private fun launchCocktailDetails(id: Int){
        Log.d(TAG, "SENT id drink = $id")
        if(activity != null){
            val intent = Intent(context, CocktailDetailsActivity::class.java)
            intent.putExtra("idDrink", id.toString())
            startActivity(intent)
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.search_cocktails){
            startActivity(Intent(context, SearchCocktailsActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }
}