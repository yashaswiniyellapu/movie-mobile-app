package com.everest.movieapp.ui.main.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.everest.movieapp.R
import com.everest.movieapp.databinding.FragmentCurrentYearMoviesBinding
import com.everest.movieapp.databinding.FragmentPopularMoviesBinding

class CurrentYearMovies:Fragment(R.layout.fragment_popular_movies) {

    private lateinit var binding : FragmentCurrentYearMoviesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = FragmentCurrentYearMoviesBinding.inflate(layoutInflater,container,false)
        return binding.root
    }
}