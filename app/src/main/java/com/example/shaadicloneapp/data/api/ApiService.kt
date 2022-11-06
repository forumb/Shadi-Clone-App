package com.example.shaadicloneapp.data.api

import com.example.shaadicloneapp.data.model.UserListDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("api/")
    suspend fun getUsers(
        @Query("results")
        results: String
    ):Response<UserListDto>
}