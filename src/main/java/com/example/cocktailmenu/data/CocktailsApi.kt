package com.example.cocktailmenu.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CocktailsApi {
    @GET("api/json/v1/1/filter.php?a=Alcoholic")
    suspend fun getAlcoholCocktails(): Response<DrinksResponse>

    @GET("api/json/v1/1/filter.php?a=Non_Alcoholic")
    suspend fun getNonAlcoholCocktails(): Response<DrinksResponse>

    @GET("api/json/v1/1/lookup.php")
    suspend fun getCocktailDetails(@Query("i") i: String): Response<DetailsCocktailResponse>

    @GET("api/json/v1/1/search.php")
    suspend fun getSearchCocktails(@Query("s") search: String): Response<DetailsCocktailResponse>
}