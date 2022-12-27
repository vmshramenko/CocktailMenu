package com.example.cocktailmenu.data

import retrofit2.Response
import retrofit2.http.GET

interface CocktailsApi {
    @GET("api/json/v1/1/filter.php?a=Alcoholic")
    suspend fun getAlcoholCocktails() : Response<DrinksResponse>

    @GET("api/json/v1/1/filter.php?a=Non_Alcoholic")
    suspend fun getNonAlcoholCocktails() : Response<DrinksResponse>

}