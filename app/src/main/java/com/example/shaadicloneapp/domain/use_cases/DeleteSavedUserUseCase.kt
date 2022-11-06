package com.example.shaadicloneapp.domain.use_cases

import com.example.shaadicloneapp.data.model.User
import com.example.shaadicloneapp.domain.repository.UserRepository

class DeleteSavedUserUseCase(private val userRepository: UserRepository) {
    suspend fun execute(user: User) = userRepository.deleteUser(user)
}