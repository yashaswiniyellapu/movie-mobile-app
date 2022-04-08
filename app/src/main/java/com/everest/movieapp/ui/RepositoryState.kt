package com.everest.movieapp.ui

import com.everest.movieapp.data.model.UiMovieDetails

sealed class RepositoryState {
    class Success(val moviesList:List<UiMovieDetails>): RepositoryState()
    class Error(val  message: String?):RepositoryState()
}