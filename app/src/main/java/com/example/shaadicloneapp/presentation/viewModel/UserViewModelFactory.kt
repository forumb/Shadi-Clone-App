package com.example.shaadicloneapp.presentation.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.shaadicloneapp.domain.use_cases.UserUseCasesWrapper

class UserViewModelFactory(
    private val app:Application,
    private val useCasesWrapper: UserUseCasesWrapper
):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return UserViewModel(
            app,
            useCasesWrapper
        ) as T
    }
}