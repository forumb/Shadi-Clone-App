package com.example.shaadicloneapp.data.repository.dataSourceImpl

import com.example.shaadicloneapp.data.db.UserDao
import com.example.shaadicloneapp.data.model.Id
import com.example.shaadicloneapp.data.model.User
import com.example.shaadicloneapp.data.repository.dataSource.UserLocalDataSource
import kotlinx.coroutines.flow.Flow

class UserLocalDataSourceImpl(private val userDao: UserDao): UserLocalDataSource {
    override suspend fun saveUsersToDB(users: List<User>) {
        userDao.saveUsers(users)
    }

    override fun getSavedUsers(): Flow<List<User>> {
        return userDao.getAllUsers()
    }

    override suspend fun deleteUserFromDB(user: User) {
        userDao.deleteUser(user)
    }

    override suspend fun deleteALl() {
        userDao.deleteAll()
    }

    override suspend fun updateAcceptedField(hasAccepted: Boolean, email: String) {
        userDao.updateAcceptedField(hasAccepted, email)
    }

    override suspend fun updateDeclineField(hasDecline: Boolean, email: String) {
        userDao.updateDeclineField(hasDecline, email)
    }

}