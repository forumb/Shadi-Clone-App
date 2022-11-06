package com.example.shaadicloneapp.domain.use_cases

import com.example.shaadicloneapp.domain.repository.UserRepository

class DeleteAllUseCase(private val userRepository: UserRepository) {

    suspend fun execute()= userRepository.deleteAll()
}