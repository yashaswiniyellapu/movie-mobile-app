package com.everest.movieapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.everest.movieapp.data.api.MovieApi
import com.everest.movieapp.data.model.Result
import com.everest.movieapp.data.repository.MovieRepository
import com.everest.movieapp.databinding.ActivitySearchScreenBinding
import com.everest.movieapp.ui.adapters.MovieRecyclerViewAdapter
import com.everest.movieapp.ui.main.viewmodel.SearchViewModel
import com.everest.movieapp.ui.main.viewmodel.ViewModelFactory
import com.everest.movieapp.utils.constants.Constants

class SearchScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchScreenBinding
    private lateinit var movieRecyclerViewAdapter: MovieRecyclerViewAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchMoviesViewModel: SearchViewModel
    private lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        recyclerView = binding.searchTv

        viewModelFactory =
            ViewModelFactory(MovieRepository(MovieApi.getInstance().create(MovieApi::class.java)))

        searchMoviesViewModel =
            ViewModelProvider(this, viewModelFactory).get(SearchViewModel::class.java)

        searchMoviesViewModel.movieList.observe(this) {
            movieRecyclerViewAdapter = MovieRecyclerViewAdapter(it, clickListener)
            recyclerView.adapter = movieRecyclerViewAdapter
        }


    }

    private val clickListener = object : MovieRecyclerViewAdapter.CustomClick {
        override fun onClick(position: Int, context: Context, movieDetails: Result) {
            val intent = Intent(context, DetailsScreenActivity::class.java)
            intent.putExtra(Constants.MOVIE_DETAILS, movieDetails)
            context.startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.nav_menu, menu)
        val menuItem = menu?.findItem(R.id.nav_search)
        val searchView = menuItem?.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                searchMoviesViewModel.setData(newText!!)
                return false
            }

        })
        return super.onPrepareOptionsMenu(menu)
    }

}