package com.everest.movieapp.api

import com.everest.movieapp.model.MovieDb
import com.everest.movieapp.model.Result
import retrofit2.Call
import retrofit2.http.GET
import java.time.LocalDate

interface Api {

    @GET("3/movie/popular?api_key=8ed49a9afc3f95499f3e4aed8eed5a33")
    fun getPopularViews():Call<MovieDb>

    @GET("3/movie/popular?api_key=8ed49a9afc3f95499f3e4aed8eed5a33&primary_release_year=2022")
    fun getCurrentYearMovies():Call<MovieDb>
}