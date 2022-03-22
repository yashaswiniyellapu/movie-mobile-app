package com.everest.movieapp.ui.main.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.everest.movieapp.R
import com.everest.movieapp.api.Api
import com.everest.movieapp.api.RetrofitHelper
import com.everest.movieapp.databinding.FragmentCurrentYearMoviesBinding
import com.everest.movieapp.model.MovieDb
import com.everest.movieapp.adapters.MovieRecyclerViewAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CurrentYearMovies : Fragment(R.layout.fragment_popular_movies) {

    private lateinit var binding: FragmentCurrentYearMoviesBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var movieRecyclerViewAdapter: MovieRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCurrentYearMoviesBinding.inflate(layoutInflater, container, false)
        recyclerView = binding.currentYearRecyclerView

        populateData()

        return binding.root
    }

    private fun populateData() {

        val api: Api = RetrofitHelper.getInstance().create(Api::class.java)
        val currentYearMovieData = api.getCurrentYearMovies()

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

}
