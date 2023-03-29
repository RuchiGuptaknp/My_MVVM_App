package com.example.mymvvmflowdemo.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymvvmflowdemo.model.UserDetailResponse
import com.example.mymvvmflowdemo.network.ApiClient
import com.example.mymvvmflowdemo.repository.UserRepository
import com.example.mymvvmflowdemo.utils.ApiState
import com.example.mymvvmflowdemo.utils.Status
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class UserViewModel:ViewModel() {
    //create the repository and pass the api
    private val repository=UserRepository(ApiClient.ApiService())
    val userResponse=UserDetailResponse()
    val userState = MutableStateFlow(
        ApiState(
            Status.LOADING,
           UserDetailResponse(), ""
        )
    )

    init {
        // Initiate a starting
        // search with comment Id 1
        getUserDetail(1)
    }


    // Function to get new Comments
    fun getUserDetail(id: Int) {

        // Since Network Calls takes time,Set the
        // initial value to loading state
        userState.value = ApiState.loading()

        // ApiCalls takes some time, So it has to be
        // run and background thread. Using viewModelScope
        // to call the api
        viewModelScope.launch {

            // Collecting the data emitted
            // by the function in repository
            repository.getUserDetail(id)
                // If any errors occurs like 404 not found
                // or invalid query, set the state to error
                // State to show some info
                // on screen
                .catch {
                    userState.value =
                        ApiState.error(it.message.toString())
                }
                // If Api call is succeeded, set the State to Success
                // and set the response data to data received from api
                .collect {
                    userState.value = ApiState.success(it.data)
                }
        }
    }
}
