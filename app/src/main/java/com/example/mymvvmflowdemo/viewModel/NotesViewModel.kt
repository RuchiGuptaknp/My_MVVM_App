package com.example.mymvvmflowdemo.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mymvvmflowdemo.model.Blog

class NotesViewModel:ViewModel() {
    var list=MutableLiveData<ArrayList<Blog>>()
    var newList= arrayListOf<Blog>()

    fun add(notes: Blog){
        newList.add(notes)
        list.value=newList
    }
    fun remove(notes:Blog){
        newList.remove(notes)
        list.value=newList
    }


}