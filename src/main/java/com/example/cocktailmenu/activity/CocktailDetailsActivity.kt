package com.example.cocktailmenu.activity

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cocktailmenu.adapter.IngredientsAdapter
import com.example.cocktailmenu.R
import com.example.cocktailmenu.viewModel.CocktailDetailsViewModel

open class CocktailDetailsActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cocktail_details)
        val detailsViewModel: CocktailDetailsViewModel
        val cocktailName: TextView = findViewById(R.id.cocktail_name)
        val imageCocktail: ImageView = findViewById(R.id.cocktail_image)
        val idDrink = intent.getStringExtra("idDrink")
        val ingredientsRecycler = findViewById<RecyclerView>(R.id.ingredients_recycler)
        val instruction: TextView = findViewById(R.id.instruction_details)
        val serve: TextView = findViewById(R.id.serve_info)

        detailsViewModel = ViewModelProvider(
            this,
            viewModelFactory { CocktailDetailsViewModel(idDrink!!) }
        )[CocktailDetailsViewModel::class.java]
        val adapter = IngredientsAdapter(emptyList())
        ingredientsRecycler.adapter = adapter

        lifecycleScope.launchWhenStarted {
            detailsViewModel.details.collect {
                if (it.details != null) {
                    adapter.setCocktails(it.details.ingredients)
                    cocktailName.text = it.details.name
                    Glide.with(imageCocktail.context)
                        .load(it.details.thumbDrink)
                        .into(imageCocktail)
                    instruction.text = it.details.instruction
                    serve.text = it.details.serve
                }
            }
        }

        Log.d(TAG, "GET id drink = $idDrink")
    }

    private inline fun <VM : ViewModel> viewModelFactory(crossinline f: () -> VM) =
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T = f() as T
        }

}


