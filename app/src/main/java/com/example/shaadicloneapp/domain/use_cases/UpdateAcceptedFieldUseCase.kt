package com.example.shaadicloneapp.domain.use_cases

import com.example.shaadicloneapp.data.model.Id
import com.example.shaadicloneapp.domain.repository.UserRepository

class UpdateAcceptedFieldUseCase(private val userRepository: UserRepository) {

    suspend fun execute(hasAccepted: Boolean, email: String) = userRepository.updateAcceptedField(hasAccepted, email)
}