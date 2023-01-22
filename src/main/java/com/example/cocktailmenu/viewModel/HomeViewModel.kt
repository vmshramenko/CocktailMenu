package com.example.cocktailmenu.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cocktailmenu.data.Drink
import com.example.cocktailmenu.data.DrinksResponse
import com.example.cocktailmenu.model.CocktailFragmentState
import com.example.cocktailmenu.model.CocktailModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.Response

class HomeViewModel : ViewModel() {
    private var cocktailModel = CocktailModel()
    val cocktail = MutableStateFlow<CocktailFragmentState<List<Drink>>
            >(
        CocktailFragmentState(
            null, null, null, true
        )
    )
    val nonAlcoholCocktail = MutableStateFlow<CocktailFragmentState<List<Drink>>
            >(
        CocktailFragmentState(
            null, null, null, true
        )
    )

    init {
        fetchAlcoholCocktail()
        fetchNonAlcoholCocktail()
    }

    private fun fetchAlcoholCocktail() {
        viewModelScope.launch {
            val response = cocktailModel.getAlcoholCocktail()
            processCocktails(response)
        }
    }

    private fun fetchNonAlcoholCocktail() {
        viewModelScope.launch {
            val response = cocktailModel.getNonAlcoholCocktail()
            processNonAlcoholCocktails(response)
        }
    }

    private fun processCocktails(response: Response<DrinksResponse>) {
        if (response.isSuccessful) {
            cocktail.update { it.copy(cocktails = response.body()?.drinks) }
        } else {
            cocktail.update {
                it.copy(
                    error = response.code(),
                    errorMessage = response.errorBody().toString()
                )
            }
        }
    }

    private fun processNonAlcoholCocktails(response: Response<DrinksResponse>) {
        if (response.isSuccessful) {
            nonAlcoholCocktail.update { it.copy(cocktails = response.body()?.drinks) }
        } else {
            nonAlcoholCocktail.update {
                it.copy(
                    error = response.code(),
                    errorMessage = response.errorBody().toString()
                )
            }
        }
    }

}