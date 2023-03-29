package com.example.mymvvmflowdemo.activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mymvvmflowdemo.adapter.NotesAdapter
import com.example.mymvvmflowdemo.databinding.ActivityNotesBinding
import com.example.mymvvmflowdemo.model.Blog
import com.example.mymvvmflowdemo.repository.NotesRepository
import com.example.mymvvmflowdemo.utils.showToast
import com.example.mymvvmflowdemo.viewModel.NotesViewModel


class NotesActivity:AppCompatActivity() {
    private val binding by lazy {ActivityNotesBinding.inflate(layoutInflater)  }
    private lateinit var viewModel: NotesViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this,NotesRepository()).get(NotesViewModel::class.java)
        binding.button.setOnClickListener {
            addDataInTextField()
        }
        initAdapter()
    }

    private fun addDataInTextField() {
        val notes=binding.titletxt.text.toString()
        if (notes.isBlank()){
            showToast("TextField can not empty")
        }else{
            val blog=Blog(notes)
            viewModel.add(blog)
            binding.titletxt.text.clear()

        }
    }

    private fun initAdapter() {
        binding.recycler.layoutManager=LinearLayoutManager(this)
        observerData()
        binding.recycler.adapter?.notifyDataSetChanged()


    }

    private fun observerData() {
        viewModel.list.observe(this, Observer {
            binding.recycler.adapter=NotesAdapter(viewModel,it,this)

        })
    }
}