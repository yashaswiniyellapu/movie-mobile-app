package com.everest.movieapp.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.everest.movieapp.data.api.MovieApi
import com.everest.movieapp.data.model.MovieDb
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepository {
    private val movieApi = MovieApi.getInstance().create(MovieApi::class.java)

    var movieList = MutableLiveData<MovieDb>()
    fun getPopularMovies() {
        getResponse(movieApi.getPopularViews())
    }

    fun getCurrentYearMovies() {
        getResponse(movieApi.getCurrentYearMovies())
    }

    private fun getResponse(movies: Call<MovieDb>) {
        movies.enqueue(object : Callback<MovieDb> {
            override fun onResponse(call: Call<MovieDb>, response: Response<MovieDb>) {
                movieList.value = response.body()
            }
            override fun onFailure(call: Call<MovieDb>, t: Throwable) {
                Log.i("hello", "failure")
            }

        })
    }


}