package com.everest.movieapp.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.everest.movieapp.data.model.Result
import retrofit2.http.GET

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(movieData: List<Result>)

//    @GET
//    fun getAllMovies()
}