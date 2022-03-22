package com.everest.movieapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.everest.movieapp.databinding.ActivityDetailsScreenBinding
import com.everest.movieapp.model.Result

class DetailsScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailsScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dataSet = intent.getParcelableExtra<Result>("dataset")
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/original/" + dataSet?.poster_path)
            .into(binding.imageViewTv)
        binding.titleTv.text = dataSet?.title
        binding.descriptionTv.text = dataSet?.overview
        binding.popularityTv.text = dataSet?.popularity.toString()
        binding.releaseDateTv.text = dataSet?.release_date
        binding.voteAverageTv.text = dataSet?.vote_average.toString()
    }
}