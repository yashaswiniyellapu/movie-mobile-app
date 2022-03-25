package com.everest.movieapp.data.api

import com.everest.movieapp.data.model.MovieDb
import com.everest.movieapp.utils.constants.Constants
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("3/movie/popular?api_key=8ed49a9afc3f95499f3e4aed8eed5a33")
    fun getPopularViews(): Call<MovieDb>

    @GET("3/movie/popular?api_key=8ed49a9afc3f95499f3e4aed8eed5a33&primary_release_year=2022")
    fun getCurrentYearMovies(): Call<MovieDb>

    @GET("https://api.themoviedb.org/3/search/movie")
    fun searchMovie(
        @Query("query") movieName: String,
        @Query("api_key") apiKey: String
    ): Call<MovieDb>


    companion object {
        fun getInstance(): Retrofit {
            return Retrofit.Builder().baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())  //coverts json to object
                .build()
        }
    }

}