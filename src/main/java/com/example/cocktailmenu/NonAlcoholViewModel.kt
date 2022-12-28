package com.example.cocktailmenu

import androidx.lifecycle.viewModelScope
import com.example.cocktailmenu.model.CocktailModel
import kotlinx.coroutines.launch

class NonAlcoholViewModel : BaseViewModel() {

    override fun fetchCocktailsData() {
        viewModelScope.launch {
            val response = cocktailModel.getNonAlcoholCocktail()
            processCocktails(response)
        }
    }
}