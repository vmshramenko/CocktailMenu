package com.example.cocktailmenu.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cocktailmenu.data.DetailsCocktailResponse
import com.example.cocktailmenu.data.SearchDvo
import com.example.cocktailmenu.model.CocktailModel
import com.example.cocktailmenu.model.SearchFragmentState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.Response

class SearchCocktailsViewModel(search: String) : ViewModel() {
    private val cocktailModel = CocktailModel()
    var search = MutableStateFlow(
        SearchFragmentState(
            null, null, null, true
        )
    )

    init {
        fetchCocktails(search)
    }

    private fun fetchCocktails(search: String) {
        viewModelScope.launch {
            val response = cocktailModel.getSearchCocktails(search)
            processSearch(response)
        }
    }

    private fun processSearch(response: Response<DetailsCocktailResponse>) {

        if (response.isSuccessful) {
            val searchResult = response.body()?.drinks
            val searchList: MutableList<SearchDvo> = mutableListOf()
            searchResult?.forEach {
                searchList.add(
                    SearchDvo(
                        name = it.strDrink!!,
                        category = it.strCategory!!,
                        image = it.strDrinkThumb!!,
                        idDrink = it.idDrink!!
                    )
                )
            }
            search.update { it.copy(search = searchList) }
        } else {
            search.update {
                it.copy(
                    search = null,
                    error = response.code(),
                    errorMessage = response.errorBody().toString()
                )
            }
        }
    }
}