package com.example.shaadicloneapp.data.repository.dataSource

import com.example.shaadicloneapp.data.model.Id
import com.example.shaadicloneapp.data.model.User
import kotlinx.coroutines.flow.Flow

interface UserLocalDataSource {
    suspend fun saveUsersToDB(users: List<User>)
    fun getSavedUsers(): Flow<List<User>>
    suspend fun deleteUserFromDB(user: User)
    suspend fun deleteALl()
    suspend fun updateAcceptedField(hasAccepted: Boolean, email: String)
    suspend fun updateDeclineField(hasDecline: Boolean, email: String)
}