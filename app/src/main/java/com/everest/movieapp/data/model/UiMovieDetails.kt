package com.everest.movieapp.data.model

import android.os.Parcelable
import com.everest.movieapp.utils.constants.Constants.Companion.IMAGE_BASE_URL
import kotlinx.parcelize.Parcelize
import java.lang.StringBuilder

@Parcelize
data class UiMovieDetails(val movieName:String?,
                          val releaseDate:String?,
                          val voteRate:Int?,
                          val popularity:Double?,
                          val description:String?,
                          var posterPath:String?): Parcelable