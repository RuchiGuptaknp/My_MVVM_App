package com.example.mymvvmflowdemo.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import androidx.recyclerview.widget.GridLayoutManager
import com.example.mymvvmflowdemo.adapter.MovieAdapter
import com.example.mymvvmflowdemo.databinding.MovieListBinding
import com.example.mymvvmflowdemo.viewModel.MovieViewModel


class MovieListActivity:AppCompatActivity(){
    private lateinit var binding : MovieListBinding
        private lateinit var viewModel: MovieViewModel
        private lateinit var movieAdapter : MovieAdapter
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = MovieListBinding.inflate(layoutInflater)
            setContentView(binding.root)
            prepareRecyclerView()
            viewModel = ViewModelProvider(this)[MovieViewModel::class.java]
            viewModel.getPopularMovies()
            viewModel.observeMovieLiveData().observe(this, Observer { movieList ->
                movieAdapter.setMovieList(movieList)
                binding.progressBar.visibility=View.GONE
            })
        }

        private fun prepareRecyclerView() {
            movieAdapter = MovieAdapter()
            binding.rvMovies.apply {
                layoutManager = GridLayoutManager(applicationContext,2)
                adapter = movieAdapter
            }
        }

   /* override fun setOnItemClick() {
        val intent= Intent(this,UserDetailListActivity::class.java)
        startActivity(intent)


    }*/
}
