package com.example.shaadicloneapp.domain.use_cases

import com.example.shaadicloneapp.data.model.User
import com.example.shaadicloneapp.domain.repository.UserRepository

class SaveUsersUseCase(private val userRepository: UserRepository) {
    suspend fun execute(users: List<User>) = userRepository.saveUsers(users)
}