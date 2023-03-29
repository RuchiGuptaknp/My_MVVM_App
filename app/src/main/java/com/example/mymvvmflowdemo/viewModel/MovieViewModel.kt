package com.example.mymvvmflowdemo.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mymvvmflowdemo.model.Movie
import com.example.mymvvmflowdemo.model.MovieResult
import com.example.mymvvmflowdemo.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieViewModel: ViewModel() {
    private var movieLiveData = MutableLiveData<List<MovieResult>>()
    fun getPopularMovies() {
       ApiClient.api.getMovieList(API_KEY).enqueue(object :
            Callback<Movie> {
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                if (response.body() != null) {
                    movieLiveData.value = response.body()!!.results
                } else {
                    return
                }
            }

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                Log.d("TAG", t.message.toString())
            }
        })
    }
    fun observeMovieLiveData(): LiveData<List<MovieResult>> {
        return movieLiveData

    }
    companion object{
   const val API_KEY:String="69d66957eebff9666ea46bd464773cf0"
    }
}