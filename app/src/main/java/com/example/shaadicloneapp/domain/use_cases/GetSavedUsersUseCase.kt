package com.example.shaadicloneapp.domain.use_cases

import com.example.shaadicloneapp.data.model.User
import com.example.shaadicloneapp.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow

class GetSavedUsersUseCase(private val userRepository: UserRepository) {
    suspend fun execute(): Flow<List<User>> = userRepository.getSavedUsers()
}