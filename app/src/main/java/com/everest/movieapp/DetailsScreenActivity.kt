package com.everest.movieapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.everest.movieapp.data.model.Result
import com.everest.movieapp.databinding.ActivityDetailsScreenBinding
import com.everest.movieapp.utils.constants.Constants.Companion.MOVIE_DETAILS

class DetailsScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailsScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movieData = intent.getParcelableExtra<Result>(MOVIE_DETAILS)
        Glide.with(this)
            .load(movieData?.poster_path)
            .into(binding.imageViewTv)
        binding.titleTv.text = movieData?.title
        binding.descriptionTv.text = movieData?.overview
        binding.popularityTv.text = movieData?.popularity.toString()
        binding.releaseDateTv.text = movieData?.release_date
        binding.voteAverageTv.text = movieData?.vote_average.toString()
    }
}