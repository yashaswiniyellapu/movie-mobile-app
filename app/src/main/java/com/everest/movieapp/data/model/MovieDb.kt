package com.everest.movieapp.data.model

data class MovieDb(
    val page: Int,
    var results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)