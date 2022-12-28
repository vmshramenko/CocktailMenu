package com.example.cocktailmenu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView


abstract class BaseCocktailsFragment : Fragment() {
    private lateinit var baseViewModel: BaseViewModel
    private lateinit var adapter: ApiCocktailsAdapter

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
        adapter = ApiCocktailsAdapter(emptyList())
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
}