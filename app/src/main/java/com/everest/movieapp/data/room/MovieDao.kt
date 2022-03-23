package com.everest.movieapp.data.room

import androidx.room.Dao
import androidx.room.Insert
import com.everest.movieapp.model.Result

@Dao
interface MovieDao {
    @Insert
    fun insertAll(movieData:List<Result>)
}