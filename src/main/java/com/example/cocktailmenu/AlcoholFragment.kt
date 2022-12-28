package com.example.cocktailmenu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider


class AlcoholFragment : BaseCocktailsFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_base_cocktails, container, false)
    }

    override fun initViewModel(): BaseViewModel {
        return ViewModelProvider(this)[AlcoholViewModel::class.java]
    }
}