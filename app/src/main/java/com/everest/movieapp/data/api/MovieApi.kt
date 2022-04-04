package com.everest.movieapp.data.api

import com.everest.movieapp.data.api.interceptor.ApiInterceptor
import com.everest.movieapp.data.model.MovieDb
import com.everest.movieapp.utils.constants.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("3/movie/popular")
    suspend fun getMovies(@Query("primary_release_year") currentYear: Int? = null): MovieDb

    @GET("https://api.themoviedb.org/3/search/movie")
    suspend fun searchMovie(
        @Query("query") movieName: String
    ): MovieDb


    companion object {
        private val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(ApiInterceptor())
            .build()

        fun getInstance(): Retrofit {
            return Retrofit.Builder().baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //coverts json to object
                .client(okHttpClient)
                .build()
        }
    }

}