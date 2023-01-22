package com.example.cocktailmenu.model

import com.example.cocktailmenu.data.SearchDvo

data class SearchFragmentState(
    val search: List<SearchDvo>?,
    val errorMessage: String?,
    val error: Int?,
    val loading: Boolean
)
