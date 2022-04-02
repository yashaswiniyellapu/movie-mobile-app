package com.everest.movieapp.data.model

import android.os.Parcelable
import com.everest.movieapp.utils.constants.Constants.Companion.IMAGE_BASE_URL
import kotlinx.parcelize.Parcelize
import java.lang.StringBuilder

@Parcelize
data class MovieDetails(val movieName:String,
                        val releaseDate:String,
                        val popularity:Double,
                        val description:String,
                        val posterPath:String): Parcelable