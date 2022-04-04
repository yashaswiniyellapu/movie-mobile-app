package com.everest.movieapp.data.repository

import android.content.Context
import android.net.ConnectivityManager
import com.everest.movieapp.data.api.ApplicationContextProvider
import com.everest.movieapp.data.api.MovieApi
import com.everest.movieapp.data.model.Result
import com.everest.movieapp.data.room.MovieRoomDataBase
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MovieRepository(private val movieApi: MovieApi) {
    private val context = ApplicationContextProvider.getInstance()
    private var movieRoomDataBase = MovieRoomDataBase.getDatabase(context?.applicationContext!!)

    suspend fun getPopularMovies() = flow {
        if (checkInternetConnection()) {
            val popularMovies = movieApi.getMovies().results
            persistDataIntoRoomDatabase(popularMovies)
            emit(popularMovies)
        }
        emit(movieRoomDataBase.movieDao().getPopularMovies())


    }.flowOn(IO)


    suspend fun getCurrentYearMovies() =
        flow {
            if (checkInternetConnection()) {
                val currentYearMovies = movieApi.getMovies().results
                persistDataIntoRoomDatabase(currentYearMovies)
                emit(currentYearMovies)
            }
            emit(movieRoomDataBase.movieDao().getCurrentYearMovies())
        }.flowOn(IO)

    suspend fun searchMovie(movieName: String) = flow {
        if (checkInternetConnection()) {
            val searchedMovieResult = movieApi.searchMovie(movieName).results
            emit(searchedMovieResult)
        }
        emit(movieRoomDataBase.movieDao().searchMovie(movieName))
    }

    private fun persistDataIntoRoomDatabase(movieList: List<Result>) {
        movieRoomDataBase.movieDao().insertAll(movieList)
    }

    private fun checkInternetConnection(): Boolean {
        val connectivityManager =
            context?.applicationContext!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.activeNetworkInfo != null && connectivityManager.activeNetworkInfo!!
            .isConnected
    }


}