package com.everest.movieapp.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.everest.movieapp.data.model.MovieDb
import com.everest.movieapp.data.model.Result

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(movieData: List<Result>)

    @Query("select * from result")
    fun getPopularMovies():List<Result>
    @Query("select * from result as r where strftime('%Y', r.release_date) = '2022'")
    fun getCurrentYearMovies():List<Result>
}