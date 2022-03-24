package com.everest.movieapp.data.repository

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.everest.movieapp.data.api.MovieApi
import com.everest.movieapp.data.model.MovieDb
import com.everest.movieapp.data.model.Result
import com.everest.movieapp.data.room.MovieRoomDataBase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepository(private val context: Context) {
    private val movieApi = MovieApi.getInstance().create(MovieApi::class.java)
    private  var movieRoomDataBase= MovieRoomDataBase.getDatabase(context)

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

    private fun getResponse(movies: Call<MovieDb>) {
        movies.enqueue(object : Callback<MovieDb> {
            override fun onResponse(call: Call<MovieDb>, response: Response<MovieDb>) {
                movieList.value = response.body()?.results
                persistDataIntoRoomDatabase()
                getDataFromRoomDatabase()
            }

            override fun onFailure(call: Call<MovieDb>, t: Throwable) {
                Log.i("hello", "failure")
            }

        })
    }

    fun persistDataIntoRoomDatabase() {
        movieRoomDataBase.movieDao().insertAll(movieList.value!!)
    }

    fun getDataFromRoomDatabase() {
//        Log.i("testRooomDataBase",movieRoomDataBase.movieDao().getAllMovies().toString())
    }


//    fun checkInternetConnection() {
//
//        val networkRequest = NetworkRequest.Builder()
//            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
//            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
//            .build()
//        val networkCallBack= object:ConnectivityManager.NetworkCallback() {
//            override fun onAvailable(network: Network) {
//                super.onAvailable(network)
//                Log.i("networkOn","Internet is on")
//                getResponse(movieApi.getPopularViews())
//            }
//
//            override fun onLost(network: Network) {
//                super.onLost(network)
//                Log.i("networkOff","Internet is off")
//            }
//        }
//        val connectivityManager= getSystemService(ConnectivityManager::class.java) as ConnectivityManager
//        connectivityManager.requestNetwork(networkRequest, networkCallback)
//    }

    private fun checkInternetConnection(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.activeNetworkInfo != null && connectivityManager.activeNetworkInfo!!
            .isConnected
    }


}