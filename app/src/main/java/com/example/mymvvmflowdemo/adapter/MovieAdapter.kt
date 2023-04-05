package com.example.mymvvmflowdemo.adapter

import android.location.GnssAntennaInfo
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mymvvmflowdemo.databinding.MovieLayoutBinding
import com.example.mymvvmflowdemo.model.DetailResponseItem
import com.example.mymvvmflowdemo.model.MovieResult
import com.google.android.material.animation.AnimatableView
import com.google.gson.Gson
import okhttp3.internal.http2.Http2Connection

class MovieAdapter(/*private val listener: UserClickListener*/) :RecyclerView.Adapter<MovieAdapter.ViewHolder>(){
    private var result=ArrayList<MovieResult>()


    class ViewHolder(val binding: MovieLayoutBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
      val root=MovieLayoutBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(root )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
      holder.binding.movieName.text=result[position].title
        Log.d("ResponseAdapter", Gson().toJson(result))
        Glide.with(holder.itemView).load(
            IMAGE_URL
                +result[position].poster_path).into(holder.binding.movieImage)

/*holder.binding.mainLayout.setOnClickListener {
    listener.setOnItemClick(result)

}*/

    }

    override fun getItemCount(): Int {
       return result.size
    }
    fun setMovieList( movieResult:List<MovieResult>){
        result=movieResult as ArrayList<MovieResult>
        notifyDataSetChanged()
    }

    companion object{
        const val IMAGE_URL:String="https://image.tmdb.org/t/p/w500"
    }
interface UserClickListener{
    fun setOnItemClick(list:List<MovieResult>)
    }

}
