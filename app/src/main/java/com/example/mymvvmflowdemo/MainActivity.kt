package com.example.mymvvmflowdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.mymvvmflowdemo.activity.NotesActivity
import com.example.mymvvmflowdemo.databinding.ActivityMainBinding
import com.example.mymvvmflowdemo.utils.Status
import com.example.mymvvmflowdemo.utils.showToast
import com.example.mymvvmflowdemo.viewModel.UserViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private  val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var viewModel:UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initView()
        viewModel=ViewModelProvider(this).get(UserViewModel::class.java)
        binding.button.setOnClickListener {
            if (binding.searchEditText.text.isNullOrEmpty()) {
                showToast("It can not be empty")

            } else {
                viewModel.getUserDetail(binding.searchEditText.text.toString().toInt())
            }
        }
            //Since flow run asynchronous start listening on background thread
            lifecycleScope.launch {
                viewModel.userState.collect{
                    when(it.status){
                        Status.LOADING->{
                            binding.progressBar.visibility=
                                View.VISIBLE
                        }
                        Status.SUCCESS->{
                            binding.progressBar.visibility=View.GONE
                            it.data.let {user->
                                binding.commentIdTextview.text=user?.id.toString()
                                binding.nameTextview.text=user?.name
                                binding.emailTextview.text=user?.email
                                binding.commentTextview.text=user?.comment


                            }



                        }
                        else->{
                            binding.progressBar.visibility=View.GONE
                            showToast("${it.message}")

                        }
                    }
                }
            }



        }

    private fun initView() {
        binding.btnSubmit.setOnClickListener {
            val intent=Intent(this, NotesActivity::class.java)
            startActivity(intent)
        }
    }


}
