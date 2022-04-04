package com.everest.movieapp.ui.main.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.everest.movieapp.data.model.UiMovieDetails
import com.everest.movieapp.data.repository.MovieRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class SearchViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    private var _movieList = MutableLiveData<List<UiMovieDetails>>()

    //    private var movieRepository = MovieRepository(context)
    val movieList: LiveData<List<UiMovieDetails>>
        get() = _movieList


    fun setData(movieName: String) {
        viewModelScope.launch {
            movieRepository.searchMovie(movieName)
                .catch { ex -> Log.i("SearchedViewEx", ex.toString()) }
                .collect { movie ->
                    _movieList.value = movie
                }

        }


    }
    //Log.i("testData", movieRepository.movieList.value.toString())

}
