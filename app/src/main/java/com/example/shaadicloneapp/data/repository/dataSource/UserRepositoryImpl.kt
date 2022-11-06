package com.example.shaadicloneapp.data.repository.dataSource

import com.example.shaadicloneapp.data.model.Id
import com.example.shaadicloneapp.data.model.User
import com.example.shaadicloneapp.data.model.UserListDto
import com.example.shaadicloneapp.data.utils.NetworkResponse
import com.example.shaadicloneapp.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class UserRepositoryImpl(
    private val userLocalDataSource: UserLocalDataSource,
    private val userRemoteDataSource: UserRemoteDataSource
): UserRepository {
    override suspend fun getUsers(result: String): NetworkResponse<UserListDto> {
        return sendResponse(userRemoteDataSource.getUsers(result))
    }

    override suspend fun saveUsers(user: List<User>) {
        userLocalDataSource.saveUsersToDB(user)
    }

    override suspend fun deleteUser(user: User) {
        userLocalDataSource.deleteUserFromDB(user)
    }

    override fun getSavedUsers(): Flow<List<User>> {
        return userLocalDataSource.getSavedUsers()
    }

    override suspend fun deleteAll() {
        userLocalDataSource.deleteALl()
    }

    override suspend fun updateAcceptedField(hasAccepted: Boolean, email: String) {
        userLocalDataSource.updateAcceptedField(hasAccepted, email)
    }

    override suspend fun updateDeclineField(hasDecline: Boolean, email: String) {
        userLocalDataSource.updateDeclineField(hasDecline, email)
    }

    private fun sendResponse(response:Response<UserListDto>):NetworkResponse<UserListDto>{
        if(response.isSuccessful){
            response.body()?.let { result->
                return NetworkResponse.Success(result)
            }
        }
        return NetworkResponse.Error(response.message())
    }
}