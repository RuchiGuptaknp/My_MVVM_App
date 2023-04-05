package com.example.mymvvmflowdemo.adapter

import android.view.LayoutInflater

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mymvvmflowdemo.databinding.ItemDetailUserBinding

import com.example.mymvvmflowdemo.model.DetailResponseItem

class UserAdapter(private val user:ArrayList<DetailResponseItem>):RecyclerView.Adapter<UserAdapter.ViewHolder>(){
    class ViewHolder( val binding: ItemDetailUserBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
     val root=ItemDetailUserBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder (root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.Nametitle.text=user[position].userId.toString()
        holder.binding.title.text=user[position].title
        holder.binding.body.text=user[position].body


    }

    override fun getItemCount(): Int {
        return user.size

    }
    fun getUpdateData(list:List<DetailResponseItem>){
       user.addAll(list)
    }


}