package com.example.shaadicloneapp.data.repository.dataSource

import com.example.shaadicloneapp.data.model.UserListDto
import com.example.shaadicloneapp.data.utils.NetworkResponse
import retrofit2.Response


interface UserRemoteDataSource {
    suspend fun getUsers(result : String): Response<UserListDto>
}