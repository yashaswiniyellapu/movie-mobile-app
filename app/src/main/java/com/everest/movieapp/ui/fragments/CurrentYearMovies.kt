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
import com.everest.movieapp.data.repository.MovieRepository
import com.everest.movieapp.databinding.FragmentCurrentYearMoviesBinding
import com.everest.movieapp.ui.adapters.MovieRecyclerViewAdapter
import com.everest.movieapp.ui.main.viewmodel.CurrentYearMoviesViewModel
import com.everest.movieapp.ui.main.viewmodel.ViewModelFactory

class CurrentYearMovies : Fragment(R.layout.fragment_popular_movies) {

    private lateinit var binding: FragmentCurrentYearMoviesBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var movieRecyclerViewAdapter: MovieRecyclerViewAdapter
    private lateinit var currentYearMoviesViewModel: CurrentYearMoviesViewModel
    private lateinit var viewModelFactory: ViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentCurrentYearMoviesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.currentYearRecyclerView
        viewModelFactory = ViewModelFactory(MovieRepository(requireContext()))
        currentYearMoviesViewModel =
            ViewModelProvider(this, viewModelFactory)[CurrentYearMoviesViewModel::class.java]
        val movieList: List<Result> = ArrayList()
        movieRecyclerViewAdapter = MovieRecyclerViewAdapter(movieList)
        currentYearMoviesViewModel.moviesLiveData.observe(viewLifecycleOwner)
        {
            movieRecyclerViewAdapter = MovieRecyclerViewAdapter(it)
            recyclerView.adapter = movieRecyclerViewAdapter
        }
    }

}
