package com.everest.movieapp.ui.main.viewmodel


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.everest.movieapp.data.model.Result
import com.everest.movieapp.data.model.UiMovieDetails
import com.everest.movieapp.data.repository.MovieRepository
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


class PopularMoviesViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    private var _movieList = MutableLiveData<List<UiMovieDetails>>()
    val moviesList: LiveData<List<UiMovieDetails>>
        get() = _movieList


    init {
        viewModelScope.launch {
            movieRepository.getPopularMovies()
                .catch { e-> Log.i("PopularViewModelEx",e.toString()) }
                .collect { movie->
                _movieList.value = movie
            }
        }
    }

}

