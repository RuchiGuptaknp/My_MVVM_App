package com.example.mymvvmflowdemo.model
data class Movie(
    val page: Int?=null,
    var results: List<MovieResult>?=null,
    val total_pages: Int?=null,
    val total_results: Int?=null
)