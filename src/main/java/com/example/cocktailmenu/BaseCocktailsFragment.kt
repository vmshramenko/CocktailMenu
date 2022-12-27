package com.example.cocktailmenu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"



abstract class BaseCocktailsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var baseViewModel: BaseViewModel
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_base_cocktails, container, false)
    }

    abstract fun initViewModel(): BaseViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        baseViewModel = initViewModel()
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerview)
        adapter = ApiCocktailsAdapter(emptyList())
        recyclerView.adapter = adapter
        lifecycleScope.launchWhenStarted {
            baseViewModel.cocktail.collect{
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
}
   /* companion object {

        // TODO: Rename and change types and number of parameters
        /*@JvmStatic
        fun newInstance(param1: String, param2: String) =
             BaseCocktailsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }*/
    }
}*/