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

class SearchViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    private val _error = MutableLiveData<String?>()
    private var _movieList = MutableLiveData<List<UiMovieDetails>>()
    val movieList: LiveData<List<UiMovieDetails>> = _movieList

    val error: LiveData<String?> = _error
    fun setData(movieName: String?) {
        viewModelScope.launch {
            Dispatchers.IO
            when (val data = movieRepository.searchMovie(movieName)) {
                is RepositoryState.Success ->
                    _movieList.postValue(data.moviesList)
                is RepositoryState.Error -> _error.postValue(data.message)
            }
        }
    }

}
