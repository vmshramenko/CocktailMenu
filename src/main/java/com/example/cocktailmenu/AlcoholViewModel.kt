package com.example.cocktailmenu

import androidx.lifecycle.ViewModel

class AlcoholViewModel : ViewModel() {
    private val image: Int = R.drawable.image_alc
    private val text = "Item"

    fun getImage():Int{
        return image
    }

    fun getText():String{
        return text
    }
}