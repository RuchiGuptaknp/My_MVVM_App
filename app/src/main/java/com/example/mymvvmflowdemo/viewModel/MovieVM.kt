package com.example.mymvvmflowdemo.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymvvmflowdemo.model.Movie

import com.example.mymvvmflowdemo.network.ApiClient
import com.example.mymvvmflowdemo.repository.MovieRepository
import com.example.mymvvmflowdemo.utils.ApiState
import com.example.mymvvmflowdemo.utils.Status
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch


class MovieVM :ViewModel(){
    private val repository=MovieRepository(ApiClient.ApiServiceClass())
     val movieList= MutableStateFlow(ApiState(Status.LOADING,Movie(),""))

init{
    getMovieDetail()
}

     fun getMovieDetail() {
        movieList.value=ApiState.loading()
        viewModelScope.launch {
          repository.getMovieList()
              .catch {
                  movieList.value=ApiState.error(it.message.toString())
              }
              .collect{
                  movieList.value=ApiState.success(it.data)
              }

        }
    }


}