package com.everest.movieapp.ui.main.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.everest.movieapp.data.model.UiMovieDetails
import com.everest.movieapp.data.repository.MovieRepository
import com.everest.movieapp.ui.RepositoryState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class PopularMoviesViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    private var _movieList = MutableLiveData<List<UiMovieDetails>>()
    val moviesList: LiveData<List<UiMovieDetails>> = _movieList

    private var _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error


    init {
        setData()
    }
    private fun setData() {
        viewModelScope.launch {
            Dispatchers.IO
            when (val data = movieRepository.getPopularMovies()) {
                is RepositoryState.Success -> {
                    _movieList.postValue(data.moviesList)
                }
                is RepositoryState.Error -> _error.postValue(data.message)
            }
        }

    }

}

