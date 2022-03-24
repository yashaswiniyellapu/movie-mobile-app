package com.everest.movieapp.ui.main.viewmodel


import android.content.Context
import androidx.lifecycle.*
import com.everest.movieapp.data.model.Result
import com.everest.movieapp.data.repository.MovieRepository

class PopularMoviesViewModel(context: Context) :ViewModel() {
    private var moviesMutableLiveData = MutableLiveData<List<Result>>()
    private  var movieRepository= MovieRepository(context)
    val moviesLiveData: LiveData<List<Result>>
        get() = moviesMutableLiveData


    init {
        setData()
    }

    private fun setData() {

        movieRepository.getPopularMovies()
        movieRepository.movieList.observeForever {
            moviesMutableLiveData.value = movieRepository.movieList.value?.results
        }

    }

}

