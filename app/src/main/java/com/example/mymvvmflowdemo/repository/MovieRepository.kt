package com.example.mymvvmflowdemo.repository


import com.example.mymvvmflowdemo.model.Movie

import com.example.mymvvmflowdemo.network.ApiInterface
import com.example.mymvvmflowdemo.utils.ApiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


class MovieRepository (private val apiInterface: ApiInterface){
    suspend fun getMovieList():kotlinx.coroutines.flow.Flow<ApiState<Movie>>{
        return flow {
            val movie=apiInterface.getMovieList(API_KEY)
            emit(ApiState.success(movie))
        }.flowOn(Dispatchers.IO)
    }
    companion object{
        const val API_KEY:String="69d66957eebff9666ea46bd464773cf0"
    }
}