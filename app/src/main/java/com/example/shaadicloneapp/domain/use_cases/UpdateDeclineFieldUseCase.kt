package com.example.shaadicloneapp.domain.use_cases

import com.example.shaadicloneapp.domain.repository.UserRepository

class UpdateDeclineFieldUseCase(private val userRepository: UserRepository) {

    suspend fun execute(hasDecline: Boolean, email: String) = userRepository.updateDeclineField(hasDecline, email)
}