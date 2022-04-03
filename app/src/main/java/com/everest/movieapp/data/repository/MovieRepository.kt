package com.everest.movieapp.data.repository

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.everest.movieapp.data.api.MovieApi
import com.everest.movieapp.data.api.ApplicationContextProvider
import com.everest.movieapp.data.model.MovieDb
import com.everest.movieapp.data.model.Result
import com.everest.movieapp.data.room.MovieRoomDataBase
import com.everest.movieapp.ui.fragments.CurrentYearMovies
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate

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

    private fun persistDataIntoRoomDatabase(movieList:List<Result>) {
        movieRoomDataBase.movieDao().insertAll(movieList)
    }

    private fun checkInternetConnection(): Boolean {
        val connectivityManager =
            context?.applicationContext!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.activeNetworkInfo != null && connectivityManager.activeNetworkInfo!!
            .isConnected
    }


}