package com.example.cocktailmenu

import androidx.lifecycle.viewModelScope
import com.example.cocktailmenu.model.CocktailModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AlcoholViewModel : BaseViewModel() {

    private var cocktailModel = CocktailModel()


    init {
        fetchAlcoholData()
    }

    private fun fetchAlcoholData() {
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