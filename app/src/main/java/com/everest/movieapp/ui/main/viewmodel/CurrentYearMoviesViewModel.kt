package com.everest.movieapp.ui.main.viewmodel

import android.app.Application
import android.content.Context
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.*
import com.everest.movieapp.data.model.Result
import com.everest.movieapp.data.repository.MovieRepository

class CurrentYearMoviesViewModel(context: Context) : ViewModel(){
    private  var movieRepository= MovieRepository(context)
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