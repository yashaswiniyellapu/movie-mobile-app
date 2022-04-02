package com.everest.movieapp.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieCard(val movieName:String,
                     val releaseDate:String,
                     val voteRate:Int,
                     val posterPath:String) : Parcelable