package com.everest.movieapp.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UiMovieDetails(
    val movieName: String?,
    val releaseDate: String?,
    val voteRate: Int?,
    val popularity: Double?,
    val description: String?,
    var posterPath: String?
) : Parcelable