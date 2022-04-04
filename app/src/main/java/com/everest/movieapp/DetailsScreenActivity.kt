package com.everest.movieapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.everest.movieapp.data.model.Result
import com.everest.movieapp.data.model.UiMovieDetails
import com.everest.movieapp.databinding.ActivityDetailsScreenBinding
import com.everest.movieapp.utils.constants.Constants.Companion.MOVIE_DETAILS

class DetailsScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailsScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movieData = intent.getParcelableExtra<UiMovieDetails>(MOVIE_DETAILS)
        Glide.with(this)
            .load(movieData?.posterPath)
            .into(binding.imageViewTv)
        binding.titleTv.text = movieData?.movieName
        binding.descriptionTv.text = movieData?.description
        binding.popularityTv.text = movieData?.popularity.toString()
        binding.releaseDateTv.text = movieData?.releaseDate
        binding.voteAverageTv.text = movieData?.voteRate.toString()
    }
}