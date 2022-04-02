package com.everest.movieapp.data.repository

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.everest.movieapp.data.api.MovieApi
import com.everest.movieapp.data.api.ApplicationContextProvider
import com.everest.movieapp.data.model.MovieDb
import com.everest.movieapp.data.model.Result
import com.everest.movieapp.data.room.MovieRoomDataBase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate

class MovieRepository(private val movieApi: MovieApi) {
    private val context1= ApplicationContextProvider.getInstance()
    private val movieApi1 = MovieApi.getInstance().create(MovieApi::class.java)
    private var movieRoomDataBase = MovieRoomDataBase.getDatabase(context1?.applicationContext!!)


    var movieList = MutableLiveData<List<Result>>()
    fun getPopularMovies() {
        if (checkInternetConnection()) {
            getResponse(movieApi.getMovies())
        }
        movieList.value = movieRoomDataBase.movieDao().getPopularMovies()
    }


    fun getCurrentYearMovies() {
        if (checkInternetConnection()) {
            getResponse(movieApi.getMovies(LocalDate.now().year))
        }
        movieList.value = movieRoomDataBase.movieDao().getCurrentYearMovies()
    }

    fun searchMovie(movieName: String) {
        if (checkInternetConnection()) {
            getResponse(movieApi.searchMovie(movieName))
        }
        movieList.value = movieRoomDataBase.movieDao().searchMovie(movieName)
    }

    private fun getResponse(movies: Call<MovieDb>) {
        movies.enqueue(object : Callback<MovieDb> {
            override fun onResponse(call: Call<MovieDb>, response: Response<MovieDb>) {
                movieList.value = response.body()?.results
                persistDataIntoRoomDatabase()
            }

            override fun onFailure(call: Call<MovieDb>, t: Throwable) {
                Toast.makeText(context1?.applicationContext!!, "No API response", Toast.LENGTH_LONG).show()
            }

        })
    }

    private fun persistDataIntoRoomDatabase() {
        movieRoomDataBase.movieDao().insertAll(movieList.value!!)
    }

    private fun checkInternetConnection(): Boolean {
        val connectivityManager =
            context1?.applicationContext!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.activeNetworkInfo != null && connectivityManager.activeNetworkInfo!!
            .isConnected
    }


}