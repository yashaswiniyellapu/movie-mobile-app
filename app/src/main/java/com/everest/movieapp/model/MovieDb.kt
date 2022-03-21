package com.everest.movieapp.model

data class MovieDb(
    val page: Int,
    val results: ArrayList<Result>,
    val total_pages: Int,
    val total_results: Int
)