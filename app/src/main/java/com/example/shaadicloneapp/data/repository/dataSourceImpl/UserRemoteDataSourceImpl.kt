package com.example.shaadicloneapp.data.repository.dataSourceImpl

import com.example.shaadicloneapp.data.api.ApiService
import com.example.shaadicloneapp.data.model.UserListDto
import com.example.shaadicloneapp.data.repository.dataSource.UserRemoteDataSource
import retrofit2.Response

class UserRemoteDataSourceImpl(private val apiService: ApiService): UserRemoteDataSource {
    override suspend fun getUsers(result: String): Response<UserListDto> {
        return apiService.getUsers(result)
    }
}