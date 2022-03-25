package com.everest.movieapp.ui.main.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.everest.movieapp.data.model.Result
import com.everest.movieapp.data.repository.MovieRepository

class SearchViewModel(context:Context) :ViewModel(){
    private var moviesMutableLiveData = MutableLiveData<List<Result>>()
    private  var movieRepository= MovieRepository(context)
    val moviesLiveData: LiveData<List<Result>>
        get() = moviesMutableLiveData


     fun setData(movieName:String) {

        movieRepository.searchMovie(movieName)
        movieRepository.movieList.observeForever {
            moviesMutableLiveData.value = movieRepository.movieList.value
        }
        Log.i("testData",movieRepository.movieList.value.toString())

    }
}