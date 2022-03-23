package com.everest.movieapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.everest.movieapp.R
import com.everest.movieapp.data.model.Result
import com.everest.movieapp.databinding.FragmentPopularMoviesBinding
import com.everest.movieapp.ui.adapters.MovieRecyclerViewAdapter
import com.everest.movieapp.ui.main.viewmodel.PopularMoviesViewModel

class PopularMovies : Fragment(R.layout.fragment_current_year_movies) {

    private lateinit var binding: FragmentPopularMoviesBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var movieRecyclerViewAdapter: MovieRecyclerViewAdapter
    private lateinit var popularMoviesViewModel: PopularMoviesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPopularMoviesBinding.inflate(layoutInflater, container, false)

        recyclerView = binding.popularRecyclerView
        popularMoviesViewModel = ViewModelProvider(
            this,
        )[PopularMoviesViewModel::class.java]
        val movieList: List<Result> = ArrayList()
        movieRecyclerViewAdapter =
            MovieRecyclerViewAdapter(movieList)


        popularMoviesViewModel.moviesLiveData.observe(viewLifecycleOwner) {
            movieRecyclerViewAdapter =
                MovieRecyclerViewAdapter(it)
            recyclerView.adapter = movieRecyclerViewAdapter
        }


        return binding.root
    }

}