package com.example.cocktailmenu.model

import android.util.Log
import com.example.cocktailmenu.data.AlcoholApi
import com.example.cocktailmenu.data.DrinksResponse
import com.example.cocktailmenu.data.RetrofitHelper
import retrofit2.Response

class CocktailModel {

    suspend fun getApiCocktail(): Response<DrinksResponse> {

        val quotesApi = RetrofitHelper.getInstance()
            .create(AlcoholApi::class.java)
        val result = quotesApi.getAlcoholDrinks()
        Log.d("drinks: ", result.body().toString())

        return result
    }
}