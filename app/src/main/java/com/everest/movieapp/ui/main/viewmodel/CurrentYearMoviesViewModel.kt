package com.everest.movieapp.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.everest.movieapp.data.model.Result
import com.everest.movieapp.data.repository.MovieRepository

class CurrentYearMoviesViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    private var moviesMutableLiveData = MutableLiveData<List<Result>>()
    val moviesLiveData: LiveData<List<Result>>
        get() = moviesMutableLiveData

    init {
        setData()
    }

    private fun setData() {
        movieRepository.getCurrentYearMovies()
        movieRepository.movieList.observeForever(
            Observer {
                moviesMutableLiveData.value = movieRepository.movieList.value!!
            }

        )

    }

}