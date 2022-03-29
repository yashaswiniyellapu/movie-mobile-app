package com.everest.movieapp.data.api

import com.everest.movieapp.data.api.interceptor.ApiNetworkInterceptor
import com.everest.movieapp.data.model.MovieDb
import com.everest.movieapp.utils.constants.Constants
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("3/movie/popular")
    fun getMovies(@Query("primary_release_year") currentYear: Int? = null): Call<MovieDb>

    @GET("https://api.themoviedb.org/3/search/movie")
    fun searchMovie(
        @Query("query") movieName: String
    ): Call<MovieDb>


    companion object {
        private val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(ApiNetworkInterceptor())
            .build()

        fun getInstance(): Retrofit {
            return Retrofit.Builder().baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //coverts json to object
                .client(okHttpClient)
                .build()
        }
    }

}