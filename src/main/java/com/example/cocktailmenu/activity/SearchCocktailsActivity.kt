package com.example.cocktailmenu.activity

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.cocktailmenu.R
import com.example.cocktailmenu.adapter.SearchCocktailsAdapter
import com.example.cocktailmenu.viewModel.SearchCocktailsViewModel

class SearchCocktailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_cocktails)
        val search = findViewById<SearchView>(R.id.search_cocktails)
        val recyclerSearch = findViewById<RecyclerView>(R.id.recycler_search)
        val textError = findViewById<TextView>(R.id.notFound)
        var searchViewModel: SearchCocktailsViewModel
        val adapter =
            SearchCocktailsAdapter(emptyList(), clickListener = { id -> launchCocktailDetails(id) })
        recyclerSearch.adapter = adapter

        searchViewModel = ViewModelProvider(
            this,
            viewModelFactory { SearchCocktailsViewModel(search.query.toString()) }
        )[SearchCocktailsViewModel::class.java]

        lifecycleScope.launchWhenStarted {
            searchViewModel.search.collect {
                if (it.search != null) {
                    textError.isVisible = false
                    recyclerSearch.isVisible = true
                    adapter.setCocktails(it.search)
                    Log.d("adapter set result", it.search.toString())
                    if (it.search.isEmpty()) {
                        adapter.setCocktails(emptyList())
                        recyclerSearch.isVisible = false
                        textError.isVisible = true
                    }
                }
            }
        }
        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                searchViewModel = SearchCocktailsViewModel(query)

                lifecycleScope.launchWhenStarted {
                    searchViewModel.search.collect {
                        if (it.search != null) {
                            textError.isVisible = false
                            recyclerSearch.isVisible = true
                            adapter.setCocktails(it.search)
                            Log.d("adapter set result", it.search.toString())
                            if (it.search.isEmpty()) {
                                adapter.setCocktails(emptyList())
                                recyclerSearch.isVisible = false
                                textError.isVisible = true
                            }
                        }
                    }
                }
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                searchViewModel = SearchCocktailsViewModel(newText)

                lifecycleScope.launchWhenStarted {
                    searchViewModel.search.collect {
                        if (it.search != null) {
                            textError.isVisible = false
                            recyclerSearch.isVisible = true
                            adapter.setCocktails(it.search)
                            Log.d("adapter set result", it.search.toString())
                            if (it.search.isEmpty()) {
                                adapter.setCocktails(emptyList())
                                recyclerSearch.isVisible = false
                                textError.isVisible = true
                            }
                        }
                    }
                }
                return false
            }
        })
    }

    protected inline fun <VM : ViewModel> viewModelFactory(crossinline f: () -> VM) =
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T = f() as T
        }

    private fun launchCocktailDetails(id: Int) {
        Log.d(ContentValues.TAG, "SENT id drink = $id")
        val intent = Intent(this@SearchCocktailsActivity, CocktailDetailsActivity::class.java)
        intent.putExtra("idDrink", id.toString())
        startActivity(intent)
    }
}