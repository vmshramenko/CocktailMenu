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
import com.example.cocktailmenu.data.SearchDvo

class SearchCocktailsAdapter(
    private var mList: List<SearchDvo>,
    val clickListener: (idCocktail: Int) -> Unit
) : RecyclerView.Adapter<SearchCocktailsAdapter.ViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun setCocktails(search: List<SearchDvo>) {
        mList = search
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.search_recycler_design, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val itemsViewModel = mList[position]

        Glide.with(holder.cocktailImage.context)
            .load(itemsViewModel.image)
            .into(holder.cocktailImage)

        holder.cocktailName.text = itemsViewModel.name

        holder.itemView.setOnClickListener {
            clickListener(itemsViewModel.idDrink.toInt())
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val cocktailImage: ImageView = itemView.findViewById(R.id.cocktail_image)
        val cocktailName: TextView = itemView.findViewById(R.id.cocktail_name)
    }
}