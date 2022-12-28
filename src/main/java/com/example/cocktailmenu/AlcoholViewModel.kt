package com.example.cocktailmenu

import androidx.lifecycle.viewModelScope
import com.example.cocktailmenu.model.CocktailModel
import kotlinx.coroutines.launch

class AlcoholViewModel : BaseViewModel() {

    override fun fetchCocktailsData() {
        viewModelScope.launch {
            val response = cocktailModel.getAlcoholCocktail()
            processCocktails(response)
        }
    }
}