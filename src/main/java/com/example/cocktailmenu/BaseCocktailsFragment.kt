package com.example.cocktailmenu

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.cocktailmenu.activity.CocktailDetailsActivity
import com.example.cocktailmenu.adapter.TabCocktailAdapter


abstract class BaseCocktailsFragment : Fragment() {
    private lateinit var baseViewModel: BaseViewModel
    private lateinit var adapter: TabCocktailAdapter

    abstract fun initViewModel(): BaseViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_base_cocktails, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        baseViewModel = initViewModel()
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerview)
        adapter =
            TabCocktailAdapter(emptyList(), clickListener = { id -> launchCocktailDetails(id) })
        recyclerView.adapter = adapter
        val pBar = view.findViewById<ProgressBar>(R.id.progressBar)
        lifecycleScope.launchWhenStarted {
            baseViewModel.cocktail.collect {
                when {
                    !it.cocktails.isNullOrEmpty() -> {
                        adapter.setCocktails(it.cocktails)
                        pBar.isVisible = false
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

    private fun launchCocktailDetails(id: Int) {
        Log.d(ContentValues.TAG, "SENT id drink = $id")
        if (activity != null) {
            val intent = Intent(context, CocktailDetailsActivity::class.java)
            intent.putExtra("idDrink", id.toString())
            startActivity(intent)
        }
    }
}