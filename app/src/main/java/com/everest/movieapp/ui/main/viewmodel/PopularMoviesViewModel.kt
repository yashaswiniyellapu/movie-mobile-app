package com.everest.movieapp.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.everest.movieapp.data.model.Result
import com.everest.movieapp.data.repository.MovieRepository

class PopularMoviesViewModel : ViewModel() {
    private var moviesMutableLiveData = MutableLiveData<List<Result>>()
    private var movieRepository: MovieRepository = MovieRepository()
    val moviesLiveData: LiveData<List<Result>>
        get() = moviesMutableLiveData


    init {
        setData()
    }

    private fun setData() {

        movieRepository.getPopularMovies()
        movieRepository.movieList.observeForever(
            Observer {
                moviesMutableLiveData.value = movieRepository.movieList.value?.results
            }

        )

    }

}

