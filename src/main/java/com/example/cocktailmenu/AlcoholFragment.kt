package com.example.cocktailmenu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.cocktailmenu.data.Drink

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private lateinit var alcoholViewModel: AlcoholViewModel

class AlcoholFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var adapter: ApiCocktailsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_alcohol, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerview)
        alcoholViewModel = ViewModelProvider(this)[AlcoholViewModel::class.java]
        adapter = ApiCocktailsAdapter(emptyList())
        recyclerView.adapter = adapter
        lifecycleScope.launchWhenStarted {
            alcoholViewModel.cocktail.collect{
                when{
                    !it.cocktails.isNullOrEmpty() -> {
                        adapter.setCocktails(it.cocktails)
                    }
                    !it.errorMessage.isNullOrEmpty() -> {
                        Toast.makeText(requireContext(), it.errorMessage,
                            Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AlcoholFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


}