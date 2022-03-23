package com.everest.movieapp

import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.everest.movieapp.databinding.ActivitySearchScreenBinding
import com.everest.movieapp.ui.adapters.MovieRecyclerViewAdapter
import com.everest.movieapp.ui.main.viewmodel.PopularMoviesViewModel

class SearchScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchScreenBinding
    private lateinit var movieRecyclerViewAdapter: MovieRecyclerViewAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var popularMoviesViewModel: PopularMoviesViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        recyclerView = binding.searchTv

        popularMoviesViewModel = ViewModelProvider(this).get(PopularMoviesViewModel::class.java)
        popularMoviesViewModel.moviesLiveData.observe(this) {
            movieRecyclerViewAdapter = MovieRecyclerViewAdapter(it)
            recyclerView.adapter = movieRecyclerViewAdapter
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
                movieRecyclerViewAdapter.filter.filter(newText)
                return false
            }

        })
        return super.onPrepareOptionsMenu(menu)
    }
}