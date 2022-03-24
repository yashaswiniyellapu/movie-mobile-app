package com.everest.movieapp.data.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.everest.movieapp.data.api.MovieApi
import com.everest.movieapp.data.model.MovieDb
import com.everest.movieapp.data.room.MovieRoomDataBase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepository(private val context:Context) {
    private val movieApi = MovieApi.getInstance().create(MovieApi::class.java)
    private lateinit var movieRoomDataBase:MovieRoomDataBase

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
                test()
            }
            override fun onFailure(call: Call<MovieDb>, t: Throwable) {
                Log.i("hello", "failure")
            }

        })
    }

    fun test()
    {
        Log.i("testDb",movieList.value.toString())
        movieRoomDataBase = MovieRoomDataBase.getDatabase(context)
        movieRoomDataBase.movieDao().insertAll(movieList.value?.results!!)
    }


}