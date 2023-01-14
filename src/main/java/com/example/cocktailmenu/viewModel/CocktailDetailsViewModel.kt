package com.example.cocktailmenu.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cocktailmenu.data.DetailsCocktailResponse
import com.example.cocktailmenu.data.DetailsDvo
import com.example.cocktailmenu.data.Ingredient
import com.example.cocktailmenu.model.CocktailModel
import com.example.cocktailmenu.model.DetailsFragmentState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.Response

class CocktailDetailsViewModel(id: String): ViewModel() {
    private var cocktailModel = CocktailModel()
    val details = MutableStateFlow(
        DetailsFragmentState(
            null, null, null, true
        )
    )


    init{
        fetchCocktailDetails(id)
    }

    private fun fetchCocktailDetails(id: String){
        viewModelScope.launch {
            val response = cocktailModel.getDetailCocktail(id)
            processDetails(response)
        }
    }

    private fun processDetails(response: Response<DetailsCocktailResponse>){
        if(response.isSuccessful){

            val ingredients: MutableList<Ingredient> = mutableListOf()
            val detailsDto = response.body()!!.drinks!!.first()
            if(detailsDto.strIngredient1 != null){

               ingredients.add(Ingredient(
                    thumb = "https://www.thecocktaildb.com/images/ingredients/${detailsDto.strIngredient1}-Medium.png",
                    name = detailsDto.strIngredient1,
                    measure = detailsDto.strMeasure1
                    ))
            }
            if(detailsDto.strIngredient2 != null){
                ingredients.add(Ingredient(
                    thumb = "https://www.thecocktaildb.com/images/ingredients/${detailsDto.strIngredient2}-Medium.png",
                    name = detailsDto.strIngredient2,
                    measure = detailsDto.strMeasure2
                ))
            }
            if(detailsDto.strIngredient3 != null){
                ingredients.add( Ingredient(
                    thumb = "https://www.thecocktaildb.com/images/ingredients/${detailsDto.strIngredient3}-Medium.png",
                    name = detailsDto.strIngredient3,
                    measure = detailsDto.strMeasure3
                ))
            }
            if(detailsDto.strIngredient4 != null){
               ingredients.add( Ingredient(
                    thumb = "https://www.thecocktaildb.com/images/ingredients/${detailsDto.strIngredient4}-Medium.png",
                    name = detailsDto.strIngredient4,
                    measure = detailsDto.strMeasure4
                ))
            }
            if(detailsDto.strIngredient5 != null){
                ingredients.add( Ingredient(
                    thumb = "https://www.thecocktaildb.com/images/ingredients/${detailsDto.strIngredient5}-Medium.png",
                    name = detailsDto.strIngredient5,
                    measure = detailsDto.strMeasure5
                ))
            }
            if(detailsDto.strIngredient6 != null){
                ingredients.add( Ingredient(
                    thumb = "https://www.thecocktaildb.com/images/ingredients/${detailsDto.strIngredient6}-Medium.png",
                    name = detailsDto.strIngredient6,
                    measure = detailsDto.strMeasure6
                ))
            }
            if(detailsDto.strIngredient7 != null){
                ingredients.add( Ingredient(
                    thumb = "https://www.thecocktaildb.com/images/ingredients/${detailsDto.strIngredient7}-Medium.png",
                    name = detailsDto.strIngredient7,
                    measure = detailsDto.strMeasure7
                ))
            }
            if(detailsDto.strIngredient8 != null){
                ingredients.add( Ingredient(
                    thumb = "https://www.thecocktaildb.com/images/ingredients/${detailsDto.strIngredient8}-Small.png",
                    name = detailsDto.strIngredient8,
                    measure = detailsDto.strMeasure8
                ))
            }
            if(detailsDto.strIngredient9 != null){
                ingredients.add( Ingredient(
                    thumb = "https://www.thecocktaildb.com/images/ingredients/${detailsDto.strIngredient9}-Medium.png",
                    name = detailsDto.strIngredient9,
                    measure = detailsDto.strMeasure9
                ))
            }
            if(detailsDto.strIngredient10 != null){
                ingredients.add( Ingredient(
                    thumb = "https://www.thecocktaildb.com/images/ingredients/${detailsDto.strIngredient10}-Medium.png",
                    name = detailsDto.strIngredient10,
                    measure = detailsDto.strMeasure10
                ))
            }
            if(detailsDto.strIngredient11 != null){
                ingredients.add(Ingredient(
                    thumb = "https://www.thecocktaildb.com/images/ingredients/${detailsDto.strIngredient11}-Medium.png",
                    name = detailsDto.strIngredient11,
                    measure = detailsDto.strMeasure11
                ))
            }
            if(detailsDto.strIngredient12 != null){
               ingredients.add(Ingredient(
                    thumb = "https://www.thecocktaildb.com/images/ingredients/${detailsDto.strIngredient12}-Medium.png",
                    name = detailsDto.strIngredient12,
                    measure = detailsDto.strMeasure12
                ))
            }
            if(detailsDto.strIngredient13 != null){
                ingredients.add(Ingredient(
                    thumb = "https://www.thecocktaildb.com/images/ingredients/${detailsDto.strIngredient13}-Small.png",
                    name = detailsDto.strIngredient13,
                    measure = detailsDto.strMeasure13
                ))
            }
            if(detailsDto.strIngredient14 != null){
                ingredients.add(Ingredient(
                    thumb = "https://www.thecocktaildb.com/images/ingredients/${detailsDto.strIngredient14}-Medium.png",
                    name = detailsDto.strIngredient14,
                    measure = detailsDto.strMeasure14
                ))
            }
            if(detailsDto.strIngredient15 != null){
                ingredients.add(Ingredient(
                    thumb = "https://www.thecocktaildb.com/images/ingredients/${detailsDto.strIngredient15}-Mdeium.png",
                    name = detailsDto.strIngredient15,
                    measure = detailsDto.strMeasure15
                ))
            }
            val detailsDvo = DetailsDvo(
                name = detailsDto.strDrink!!,
                thumbDrink = detailsDto.strDrinkThumb!!,
                ingredients = ingredients,
                instruction = detailsDto.strInstructions!!,
                serve = detailsDto.strGlass!!
            )

            details.update { it.copy(details = detailsDvo) }
        }else{
            details.update { it.copy(error = response.code(),
                errorMessage = response.errorBody().toString()) }
        }
    }
}