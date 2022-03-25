package com.everest.movieapp.data.repository

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.everest.movieapp.data.api.MovieApi
import com.everest.movieapp.data.model.MovieDb
import com.everest.movieapp.data.model.Result
import com.everest.movieapp.data.room.MovieRoomDataBase
import com.everest.movieapp.utils.constants.Constants.Companion.API_KEY
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepository(private val context: Context) {
    private val movieApi = MovieApi.getInstance().create(MovieApi::class.java)
    private var movieRoomDataBase = MovieRoomDataBase.getDatabase(context)


    var movieList = MutableLiveData<List<Result>>()
    fun getPopularMovies() {
        if (checkInternetConnection()) {
            getResponse(movieApi.getPopularViews())
        } else {
            movieList.value = movieRoomDataBase.movieDao().getPopularMovies()
            Log.i("testmovieList", movieList.value.toString())
        }

    }

    fun getCurrentYearMovies() {
        if (checkInternetConnection()) {
            getResponse(movieApi.getCurrentYearMovies())
        } else {
            movieList.value = movieRoomDataBase.movieDao().getCurrentYearMovies()
        }
    }

    fun searchMovie(movieName: String) {
        getResponse(movieApi.searchMovie(movieName, API_KEY))
    }

    private fun getResponse(movies: Call<MovieDb>) {
        movies.enqueue(object : Callback<MovieDb> {
            override fun onResponse(call: Call<MovieDb>, response: Response<MovieDb>) {
                movieList.value = response.body()?.results
                persistDataIntoRoomDatabase()
            }

            override fun onFailure(call: Call<MovieDb>, t: Throwable) {
                Log.i("hello", "failure")
            }

        })
    }

    private fun persistDataIntoRoomDatabase() {
        movieRoomDataBase.movieDao().insertAll(movieList.value!!)
    }

    private fun checkInternetConnection(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.activeNetworkInfo != null && connectivityManager.activeNetworkInfo!!
            .isConnected
    }


}