package com.everest.movieapp.data.room

import androidx.room.Dao
import androidx.room.Insert
import com.everest.movieapp.data.model.Result

@Dao
interface MovieDao {
    @Insert
    fun insertAll(movieData: List<Result>)
}