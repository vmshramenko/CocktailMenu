package com.example.cocktailmenu.model

import android.util.Log
import com.example.cocktailmenu.data.CocktailsApi
import com.example.cocktailmenu.data.DrinksResponse
import com.example.cocktailmenu.data.RetrofitHelper
import retrofit2.Response

class CocktailModel {

    suspend fun getAlcoholCocktail(): Response<DrinksResponse> {

        val quotesApi = RetrofitHelper.getInstance()
            .create(CocktailsApi::class.java)
        val result = quotesApi.getAlcoholCocktails()
        Log.d("alcohol: ", result.body().toString())

        return result
    }

    suspend fun getNonAlcoholCocktail(): Response<DrinksResponse> {

        val quotesApi = RetrofitHelper.getInstance()
            .create(CocktailsApi::class.java)
        val result = quotesApi.getNonAlcoholCocktails()
        Log.d("non-alcohol: ", result.body().toString())

        return result
    }
}