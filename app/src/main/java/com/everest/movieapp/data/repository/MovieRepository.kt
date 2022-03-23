package com.everest.movieapp.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.everest.movieapp.adapters.MovieRecyclerViewAdapter
import com.everest.movieapp.data.api.Api
import com.everest.movieapp.data.api.RetrofitHelper
import com.everest.movieapp.data.room.MovieDao
import com.everest.movieapp.data.room.MovieRoomDataBase
import com.everest.movieapp.model.MovieDb
import com.everest.movieapp.model.Result
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepository() {
    private lateinit var  movieDao:MovieRoomDataBase
    private lateinit var movieListFromApi:Api
    private val movieLiveData= MutableLiveData<Result>()
    val movies:LiveData<Result>
    get() = movieLiveData
    suspend fun getMovies()
    {
        val result= RetrofitHelper.getInstance().create(Api::class.java)
        val popularYearMovieData = movieListFromApi.getPopularViews()

        popularYearMovieData.enqueue(object : Callback<MovieDb> {

            override fun onResponse(call: Call<MovieDb>, response: Response<MovieDb>) {
               movieDao.movieDao().insertAll(response.body()?.results!!)
            }

            override fun onFailure(call: Call<MovieDb>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

}