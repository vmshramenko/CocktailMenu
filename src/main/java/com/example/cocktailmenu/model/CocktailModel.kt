package com.example.cocktailmenu.model

import android.util.Log
import com.example.cocktailmenu.data.CocktailsApi
import com.example.cocktailmenu.data.DetailsCocktailResponse
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
    suspend fun getDetailCocktail(i: String): Response<DetailsCocktailResponse> {

        val quotesApi = RetrofitHelper.getInstance()
            .create(CocktailsApi::class.java)
        val result = quotesApi.getCocktailDetails(i)
        Log.d("id drink", i)
        Log.d("details", result.body().toString())

        return result
    }

    suspend fun getSearchCocktails(search: String): Response<DetailsCocktailResponse>{
        val quotesApi = RetrofitHelper.getInstance()
            .create(CocktailsApi::class.java)
        val result = quotesApi.getSearchCocktails(search)
        Log.d("search line", result.body().toString())

        return result
    }
}