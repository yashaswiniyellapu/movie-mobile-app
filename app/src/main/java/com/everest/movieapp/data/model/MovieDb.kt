package com.everest.movieapp.data.model

data class MovieDb(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)