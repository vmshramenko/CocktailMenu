package com.example.cocktailmenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cocktailmenu.data.Drink
import com.example.cocktailmenu.data.DrinksResponse

class ApiCocktailsAdapter(private var mList: List<Drink>
) : RecyclerView.Adapter<ApiCocktailsAdapter.ViewHolder>() {

        fun setCocktails(cocktails: List<Drink>){
            mList = cocktails
            notifyDataSetChanged()
        }
    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val itemsViewModel = mList[position]

        // sets the image to the imageview from our itemHolder class
        //holder.imageView.setImageResource(itemsViewModel.strDrinkThumb)
        Glide.with(holder.imageView.context)
            .load(itemsViewModel.strDrinkThumb)
            .into(holder.imageView)

        // sets the text to the textview from our itemHolder class
        holder.textView.text = itemsViewModel.strDrink

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