package com.everest.movieapp.ui.main.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.everest.movieapp.R
import com.everest.movieapp.api.Api
import com.everest.movieapp.api.RetrofitHelper
import com.everest.movieapp.databinding.FragmentPopularMoviesBinding
import com.everest.movieapp.model.MovieDb
import com.everest.movieapp.model.MovieRecyclerViewAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PopularMovies:Fragment(R.layout.fragment_current_year_movies) {

    private lateinit var binding : FragmentPopularMoviesBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var movieRecyclerViewAdapter: MovieRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPopularMoviesBinding.inflate(layoutInflater,container,false)

        recyclerView= binding.popularRecyclerView

        populateData()

        return binding.root
    }

    private fun populateData() {

        val api: Api = RetrofitHelper.getInstance().create(Api::class.java)
        val currentYearMovieData = api.getPopularViews()

        currentYearMovieData.enqueue(object : Callback<MovieDb> {

            override fun onResponse(call: Call<MovieDb>, response: Response<MovieDb>) {
                movieRecyclerViewAdapter = MovieRecyclerViewAdapter(response.body()?.results!!)
                recyclerView.adapter = movieRecyclerViewAdapter

            }

            override fun onFailure(call: Call<MovieDb>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })

    }
}