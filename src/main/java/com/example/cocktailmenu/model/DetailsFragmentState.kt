package com.example.cocktailmenu.model

import com.example.cocktailmenu.data.DetailsDvo

data class DetailsFragmentState(
    val details: DetailsDvo?,
    val errorMessage: String?,
    val error: Int?,
    val loading: Boolean
)
