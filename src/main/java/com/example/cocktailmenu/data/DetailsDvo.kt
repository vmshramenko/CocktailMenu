package com.example.cocktailmenu.data

data class DetailsDvo(
    val name: String,
    val thumbDrink: String,
    val ingredients: List<Ingredient>,
    val instruction: String,
    val serve: String
)
