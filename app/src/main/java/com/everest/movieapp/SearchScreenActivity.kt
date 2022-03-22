package com.everest.movieapp

import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.everest.movieapp.api.Api
import com.everest.movieapp.api.RetrofitHelper
import com.everest.movieapp.databinding.ActivitySearchScreenBinding
import com.everest.movieapp.model.MovieDb
import com.everest.movieapp.adapters.MovieRecyclerViewAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchScreenBinding
    private lateinit var movieRecyclerViewAdapter: MovieRecyclerViewAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val api: Api = RetrofitHelper.getInstance().create(Api::class.java)
        val currentYearMovieData = api.getPopularViews()
        recyclerView = binding.searchTv

        currentYearMovieData.enqueue(object : Callback<MovieDb> {

            override fun onResponse(call: Call<MovieDb>, response: Response<MovieDb>) {
                movieRecyclerViewAdapter = MovieRecyclerViewAdapter(response.body()!!)
                recyclerView.adapter = movieRecyclerViewAdapter

            }

            override fun onFailure(call: Call<MovieDb>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.nav_menu, menu)
        val menuItem = menu?.findItem(R.id.nav_search)
        val searchView = menuItem?.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                movieRecyclerViewAdapter?.filter?.filter(newText)
                return false
            }

        })
        return super.onPrepareOptionsMenu(menu)
    }
}