package com.example.mymvvmflowdemo.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity

import androidx.lifecycle.lifecycleScope

import androidx.recyclerview.widget.GridLayoutManager
import com.example.mymvvmflowdemo.adapter.MovieAdapter
import com.example.mymvvmflowdemo.databinding.MovieListBinding
import com.example.mymvvmflowdemo.model.MovieResult
import com.example.mymvvmflowdemo.repository.MovieRepository
import com.example.mymvvmflowdemo.utils.Status
import com.example.mymvvmflowdemo.utils.showToast
import com.example.mymvvmflowdemo.viewModel.MovieVM
import com.google.gson.Gson
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class MovieListActivity:AppCompatActivity() {
    private val binding by lazy { MovieListBinding.inflate(layoutInflater) }
    private lateinit var movieAdapter : MovieAdapter
    var list: ArrayList<MovieResult> = ArrayList<MovieResult>()

    private   val  viewModel:MovieVM by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initRecyclerView()



    }


    private fun initRecyclerView() {
        binding.rvMovies.layoutManager=GridLayoutManager(this,2)
        movieAdapter= MovieAdapter()

        observeFlowData()
        binding.rvMovies.adapter=movieAdapter


    }
   private fun observeFlowData(){
        viewModel.getMovieDetail()
        lifecycleScope.launch {
            viewModel.movieList.collect{
                when(it.status){
                    Status.LOADING->{
                        binding.progressBar.visibility=View.VISIBLE
                        binding.rvMovies.visibility=View.GONE

                    }
                    Status.ERROR->{
                        binding.progressBar.visibility=View.GONE
                        showToast(it.message.toString())
                    }
                    Status.SUCCESS->{
                        binding.progressBar.visibility=View.GONE
                        it.data.let {
                            list= it!!.results as ArrayList<MovieResult>
                            Log.d("Response",Gson().toJson(list))
                            movieAdapter.setMovieList(list)



                        }
                    }
                }
            }
        }
    }
}