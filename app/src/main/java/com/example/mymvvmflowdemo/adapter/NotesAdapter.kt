package com.example.mymvvmflowdemo.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mymvvmflowdemo.R
import com.example.mymvvmflowdemo.activity.MovieListActivity
import com.example.mymvvmflowdemo.model.Blog
import com.example.mymvvmflowdemo.viewModel.NotesViewModel

class NotesAdapter(val viewModel: NotesViewModel,val arrayList: ArrayList<Blog>,val context: Context):RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): NotesAdapter.NoteViewHolder {
        val  root = LayoutInflater.from(parent.context).inflate(R.layout.item_view,parent,false)
        return NoteViewHolder(root)
    }


    override fun onBindViewHolder(holder: NotesAdapter.NoteViewHolder, position: Int) {
        val data=arrayList.get(position)
       holder.title.text=data.title
        holder.delete.setOnClickListener {
            viewModel.remove(data)
            notifyItemRemoved(arrayList.indexOf(data))
        }
        holder.itemView.setOnClickListener {
            val intent=Intent(context, MovieListActivity::class.java)
            context?.startActivity(intent)


        }


    }

    override fun getItemCount(): Int {
        return arrayList.size

    }
     inner class NoteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var title: TextView = view.findViewById(R.id.title)
        var delete: ImageButton = view.findViewById(R.id.delete)

    }






    }
