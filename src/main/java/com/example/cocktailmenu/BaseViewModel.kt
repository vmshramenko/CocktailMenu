package com.example.cocktailmenu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cocktailmenu.data.Drink
import com.example.cocktailmenu.model.CocktailFragmentState
import com.example.cocktailmenu.model.CocktailModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {
    private var cocktailModel = CocktailModel()
    public val cocktail = MutableStateFlow<CocktailFragmentState<List<Drink>>
            >(
        CocktailFragmentState(
            null, null, null, true
        )
    )

    init {
        fetchCocktailsData()
    }

    private fun fetchCocktailsData() {
        viewModelScope.launch {
            val response = cocktailModel.getAlcoholCocktail()

            if(response.isSuccessful){
                cocktail.update { it.copy(cocktails = response.body()?.drinks) }
            }else{
                cocktail.update { it.copy(error = response.code(),
                    errorMessage = response.errorBody().toString()) }
            }

        }
    }
}