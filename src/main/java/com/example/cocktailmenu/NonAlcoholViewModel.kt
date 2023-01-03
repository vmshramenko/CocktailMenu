package com.example.cocktailmenu

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class NonAlcoholViewModel : BaseViewModel() {

    override fun fetchCocktailsData() {
        viewModelScope.launch {
            val response = cocktailModel.getNonAlcoholCocktail()
            processCocktails(response)
        }
    }
}