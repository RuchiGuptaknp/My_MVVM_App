package com.example.mymvvmflowdemo.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    //Base url for api
    private const val BASE_URL="https://jsonplaceholder.typicode.com/"
    private const val Movie_BASE_URL="https://api.themoviedb.org/3/movie/"
    //create retrofit service
    fun ApiService():ApiInterface=Retrofit.Builder().baseUrl(BASE_URL).
    addConverterFactory(GsonConverterFactory.create()).build()
        .create(ApiInterface::class.java)

    fun ApiServiceClass():ApiInterface=Retrofit.Builder().baseUrl(Movie_BASE_URL).
    addConverterFactory(GsonConverterFactory.create()).build()
        .create(ApiInterface::class.java)

}