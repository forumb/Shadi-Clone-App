package com.example.shaadicloneapp.domain.use_cases

data class UserUseCasesWrapper (
    val deleteSavedUserUseCase: DeleteSavedUserUseCase,
    val getSavedUsersUseCase: GetSavedUsersUseCase,
    val getUsersUseCase: GetUsersUseCase,
    val saveUsersUseCase: SaveUsersUseCase,
    val deleteAllUseCase: DeleteAllUseCase,
    val updateAcceptedFieldUseCase: UpdateAcceptedFieldUseCase,
    val updateDeclineFieldUseCase: UpdateDeclineFieldUseCase
        )