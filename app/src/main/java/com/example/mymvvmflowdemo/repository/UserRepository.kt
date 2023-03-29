package com.example.mymvvmflowdemo.repository

import com.example.mymvvmflowdemo.model.UserDetailResponse
import com.example.mymvvmflowdemo.network.ApiInterface
import com.example.mymvvmflowdemo.utils.ApiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


class UserRepository(private val apiInterface: ApiInterface) {
    suspend fun getUserDetail(id:Int):kotlinx.coroutines.flow.Flow<ApiState<UserDetailResponse>>{
        return flow{
            //get the user detail data
            val user=apiInterface.getUserDetail(id)
            //emit the data wrapped in the helper class
            emit(ApiState.success(user))
        }.flowOn(Dispatchers.IO)
    }

}