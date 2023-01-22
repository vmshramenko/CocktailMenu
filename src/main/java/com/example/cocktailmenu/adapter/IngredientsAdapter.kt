package com.example.cocktailmenu.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cocktailmenu.R
import com.example.cocktailmenu.data.Ingredient

class IngredientsAdapter(
    private var mList: List<Ingredient>
) : RecyclerView.Adapter<IngredientsAdapter.ViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun setCocktails(ingredients: List<Ingredient>) {
        mList = ingredients
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.ingredients_recycler_design, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val itemsViewModel = mList[position]

        Glide.with(holder.imageIngredient.context)
            .load(itemsViewModel.thumb)
            .into(holder.imageIngredient)

        holder.ingredientName.text = itemsViewModel.name
        holder.measure.text = itemsViewModel.measure
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageIngredient: ImageView = itemView.findViewById(R.id.image_ingredient)
        val ingredientName: TextView = itemView.findViewById(R.id.ingredient)
        val measure: TextView = itemView.findViewById(R.id.measure)
    }
}