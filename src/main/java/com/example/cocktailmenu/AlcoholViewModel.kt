package com.example.cocktailmenu

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cocktailmenu.data.Drink
import com.example.cocktailmenu.data.DrinksResponse
import com.example.cocktailmenu.model.CocktailFragmentState
import com.example.cocktailmenu.model.CocktailModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AlcoholViewModel : ViewModel() {

    private var cocktailModel = CocktailModel()
    public val cocktail = MutableStateFlow<CocktailFragmentState<List<Drink>>
            >(
        CocktailFragmentState(
            null, null, null, true
        )
    )

    init {
        fetchAlcoholData()
    }

    private fun fetchAlcoholData() {
        viewModelScope.launch {
            val response = cocktailModel.getApiCocktail()

            if(response.isSuccessful){
                cocktail.update { it.copy(cocktails = response.body()?.drinks) }
            }else{
                cocktail.update { it.copy(error = response.code(),
                    errorMessage = response.errorBody().toString()) }
            }

        }
    }
}