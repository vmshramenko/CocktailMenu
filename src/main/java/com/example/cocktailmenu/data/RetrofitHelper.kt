package com.example.cocktailmenu.data

import com.example.cocktailmenu.model.CocktailFragmentState
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    val baseUrl = "https://www.thecocktaildb.com/"

    fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }
}