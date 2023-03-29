package com.example.mymvvmflowdemo.repository

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mymvvmflowdemo.viewModel.NotesViewModel

class NotesRepository():ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NotesViewModel::class.java)){
            return NotesViewModel() as T
    }
    throw IllegalArgumentException ("UnknownViewModel")
}


}