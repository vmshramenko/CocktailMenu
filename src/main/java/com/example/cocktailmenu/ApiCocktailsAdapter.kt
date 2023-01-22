package com.example.cocktailmenu

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cocktailmenu.data.Drink

class ApiCocktailsAdapter(
    private var mList: List<Drink>,
    val clickListener: (idCocktail: Int) -> Unit
) : RecyclerView.Adapter<ApiCocktailsAdapter.ViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun setCocktails(cocktails: List<Drink>) {
        mList = cocktails
        notifyDataSetChanged()
    }

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_home_design, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val itemsViewModel = mList[position]

        Glide.with(holder.imageView.context)
            .load(itemsViewModel.strDrinkThumb)
            .into(holder.imageView)

        holder.textView.text = itemsViewModel.strDrink

        holder.itemView.setOnClickListener {
            clickListener(itemsViewModel.idDrink)
        }

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageview)
        val textView: TextView = itemView.findViewById(R.id.textView)
    }
}