package com.example.shaadicloneapp.presentation.viewModel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.*
import com.example.shaadicloneapp.data.model.Id
import com.example.shaadicloneapp.data.model.User
import com.example.shaadicloneapp.data.model.UserListDto
import com.example.shaadicloneapp.data.utils.NetworkResponse
import com.example.shaadicloneapp.domain.use_cases.UserUseCasesWrapper
import com.example.shaadicloneapp.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class UserViewModel
@Inject constructor(
    private val app: Application,
    private val useCasesWrapper: UserUseCasesWrapper
) : AndroidViewModel(app) {

    val users: MutableLiveData<NetworkResponse<UserListDto>> = MutableLiveData()

    fun getUsers(result: String) = viewModelScope.launch(Dispatchers.IO) {
        users.postValue(NetworkResponse.Loading())
        try {
            if (isNetworkAvailable(app)) {
                val apiResult = useCasesWrapper.getUsersUseCase.execute(result)
                users.postValue(apiResult)
                deleteAll()
                apiResult.data?.let { saveUsers(it.results) }
            } else {
                users.postValue(NetworkResponse.Error(Constants.NO_INTERNET_MSG))
            }

        } catch (e: Exception) {
            users.postValue(NetworkResponse.Error(e.message.toString()))
        }

    }

    private fun isNetworkAvailable(context: Context?): Boolean {
        if (context == null) return false
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        return true
                    }
                }
            }
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                return true
            }
        }
        return false

    }


    fun saveUsers(users: ArrayList<User>) = viewModelScope.launch {
        useCasesWrapper.saveUsersUseCase.execute(users)
    }

    fun getSavedUsers() = liveData {
        useCasesWrapper.getSavedUsersUseCase.execute().collect {
            emit(it)
        }
    }

    fun deleteAll() = viewModelScope.launch {
        useCasesWrapper.deleteAllUseCase.execute()
    }

    fun updateAcceptedField(hasAccepted: Boolean, email: String) = viewModelScope.launch {
        useCasesWrapper.updateAcceptedFieldUseCase.execute(hasAccepted, email)
    }

    fun updateDeclineField(hasDecline: Boolean, email: String) = viewModelScope.launch {
        useCasesWrapper.updateDeclineFieldUseCase.execute(hasDecline, email)
    }
}