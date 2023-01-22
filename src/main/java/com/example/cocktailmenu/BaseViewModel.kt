package com.example.cocktailmenu

import androidx.lifecycle.ViewModel
import com.example.cocktailmenu.data.Drink
import com.example.cocktailmenu.data.DrinksResponse
import com.example.cocktailmenu.model.CocktailFragmentState
import com.example.cocktailmenu.model.CocktailModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import retrofit2.Response

abstract class BaseViewModel : ViewModel() {
    protected var cocktailModel = CocktailModel()
    val cocktail = MutableStateFlow<CocktailFragmentState<List<Drink>>
            >(
        CocktailFragmentState(
            null, null, null, true
        )
    )

    init {
        fetchCocktailsData()
    }

    abstract fun fetchCocktailsData()

    fun processCocktails(response: Response<DrinksResponse>){
         if(response.isSuccessful){
             cocktail.update { it.copy(cocktails = response.body()?.drinks) }
         }else{
             cocktail.update { it.copy(error = response.code(),
                 errorMessage = response.errorBody().toString()) }
         }
    }
}