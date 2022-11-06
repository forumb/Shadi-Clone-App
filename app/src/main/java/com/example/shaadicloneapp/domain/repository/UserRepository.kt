package com.example.shaadicloneapp.domain.repository

import com.example.shaadicloneapp.data.model.Id
import com.example.shaadicloneapp.data.model.User
import com.example.shaadicloneapp.data.model.UserListDto
import com.example.shaadicloneapp.data.utils.NetworkResponse
import kotlinx.coroutines.flow.Flow


interface UserRepository {
    suspend fun getUsers(result: String): NetworkResponse<UserListDto>
    suspend fun saveUsers(user: List<User>)
    suspend fun deleteUser(user: User)
    fun getSavedUsers(): Flow<List<User>>
    suspend fun deleteAll()
    suspend fun updateAcceptedField(hasAccepted: Boolean, email: String)
    suspend fun updateDeclineField(hasDecline: Boolean, email: String)
}