package com.everest.movieapp.ui.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.everest.movieapp.DetailsScreenActivity
import com.everest.movieapp.R
import com.everest.movieapp.data.api.MovieApi
import com.everest.movieapp.data.model.UiMovieDetails
import com.everest.movieapp.data.repository.MovieRepository
import com.everest.movieapp.databinding.FragmentPopularMoviesBinding
import com.everest.movieapp.ui.adapters.MovieRecyclerViewAdapter
import com.everest.movieapp.ui.main.viewmodel.PopularMoviesViewModel
import com.everest.movieapp.ui.main.viewmodel.ViewModelFactory
import com.everest.movieapp.utils.constants.Constants


class PopularMovies : Fragment(R.layout.fragment_current_year_movies) {

    private lateinit var binding: FragmentPopularMoviesBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var movieRecyclerViewAdapter: MovieRecyclerViewAdapter
    private lateinit var popularMoviesViewModel: PopularMoviesViewModel
    private lateinit var viewModelFactory: ViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPopularMoviesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.popularRecyclerView
        viewModelFactory =
            ViewModelFactory(MovieRepository(MovieApi.getInstance().create(MovieApi::class.java)))
        popularMoviesViewModel =
            ViewModelProvider(this, viewModelFactory)[PopularMoviesViewModel::class.java]

        popularMoviesViewModel.moviesList.observe(viewLifecycleOwner) {
            movieRecyclerViewAdapter =
                MovieRecyclerViewAdapter(it, clickListener)
            recyclerView.adapter = movieRecyclerViewAdapter
        }
    }

    private val clickListener = object : MovieRecyclerViewAdapter.CustomClick {
        override fun onClick(position: Int, context: Context, movieDetails: UiMovieDetails) {
            val intent = Intent(context, DetailsScreenActivity::class.java)
            intent.putExtra(Constants.MOVIE_DETAILS, movieDetails)
            context.startActivity(intent)
        }
    }

}