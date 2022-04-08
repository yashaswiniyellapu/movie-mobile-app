package com.everest.movieapp.data.repository

import com.everest.movieapp.data.api.MovieApi
import com.everest.movieapp.data.model.Result
import com.everest.movieapp.data.model.UiMovieDetails
import com.everest.movieapp.data.room.MovieDao
import com.everest.movieapp.ui.RepositoryState
import com.everest.movieapp.utils.constants.Constants.IMAGE_BASE_URL

class MovieRepository(
    private val movieApi: MovieApi,
    private val movieDao: MovieDao,
    private val connected: Boolean
) {

    suspend fun getPopularMovies(): RepositoryState {
        try {
            if (connected) {
                val popularMovies = movieApi.getMovies().results
                persistDataIntoRoomDatabase(popularMovies)
                return (RepositoryState.Success(setDataToUI(popularMovies)))
            }
            return (RepositoryState.Success(setDataToUI(movieDao.getPopularMovies())))
        } catch (e: Exception) {
            return RepositoryState.Error(e.message)
        }

    }


    suspend fun getCurrentYearMovies(): RepositoryState {
        try {
            if (connected) {
                val currentYearMovies = movieApi.getMovies().results
                persistDataIntoRoomDatabase(currentYearMovies)
                return (RepositoryState.Success(setDataToUI(currentYearMovies)))
            }
            return (RepositoryState.Success(
                setDataToUI(
                    movieDao.getCurrentYearMovies()
                )
            ))
        } catch (e: Exception) {
            return RepositoryState.Error(e.message)
        }
    }

    suspend fun searchMovie(movieName: String?): RepositoryState {
        try {

            if (connected) {
                val searchedMovieResult = movieApi.searchMovie(movieName).results
                return RepositoryState.Success(setDataToUI(searchedMovieResult))
            }
            return RepositoryState.Success(setDataToUI(movieDao.searchMovie(movieName)))
        } catch (e: Exception) {
            return RepositoryState.Error(e.message)
        }
    }

    private suspend fun persistDataIntoRoomDatabase(movieList: List<Result>) {
        movieDao.insertAll(movieList)
    }

    private fun setDataToUI(dbMovieDetails: List<Result>): List<UiMovieDetails> {
        return dbMovieDetails.map {
            UiMovieDetails(
                it.title,
                it.release_date,
                it.vote_count,
                it.popularity,
                it.overview,
                IMAGE_BASE_URL + it.poster_path
            )
        }
    }


}