package com.example.cocktailmenu.data

import retrofit2.Response
import retrofit2.http.GET

interface AlcoholApi {
    @GET("api/json/v1/1/filter.php?a=Alcoholic")
    suspend fun getAlcoholDrinks() : Response<DrinksResponse>
}