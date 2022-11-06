package com.example.shaadicloneapp.domain.use_cases

import com.example.shaadicloneapp.data.model.UserListDto
import com.example.shaadicloneapp.data.utils.NetworkResponse
import com.example.shaadicloneapp.domain.repository.UserRepository

class GetUsersUseCase(private val userRepository: UserRepository) {
    suspend fun execute(result: String): NetworkResponse<UserListDto> = userRepository.getUsers(result)
}