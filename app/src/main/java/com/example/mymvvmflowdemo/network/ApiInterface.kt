package com.example.mymvvmflowdemo.network

import com.example.mymvvmflowdemo.model.Movie
import com.example.mymvvmflowdemo.model.UserDetailResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {
    @GET("/comments/{id}")
suspend fun getUserDetail(@Path("id")id:Int):UserDetailResponse
@GET("popular?")
 fun getMovieList(@Query("api_key")apiKey:String):Call<Movie>
}