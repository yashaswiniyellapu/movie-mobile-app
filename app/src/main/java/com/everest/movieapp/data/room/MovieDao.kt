package com.everest.movieapp.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.everest.movieapp.data.model.Result

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movieData: List<Result>)

    @Query("select * from result where popularity > 4")
    suspend fun getPopularMovies(): List<Result>

    @Query("select * from result as r where strftime('%Y', r.release_date)=strftime('%Y', 'now')")
    suspend fun getCurrentYearMovies(): List<Result>

    @Query("select * from result as r where title like  '%'||:movieName ||'%'")
    suspend fun searchMovie(movieName: String): List<Result>
}