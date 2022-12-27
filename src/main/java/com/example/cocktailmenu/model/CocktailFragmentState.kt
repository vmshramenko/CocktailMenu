package com.example.cocktailmenu.model

import com.example.cocktailmenu.data.Drink

data class CocktailFragmentState<T>(
    val cocktails: List<Drink>?,
    val errorMessage: String?,
    val error: Int?, val loading: Boolean
) {
}
