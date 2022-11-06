package com.example.shaadicloneapp.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.shaadicloneapp.data.api.ApiClient
import com.example.shaadicloneapp.data.api.ApiService
import com.example.shaadicloneapp.data.db.UserDao
import com.example.shaadicloneapp.data.db.UserDatabase
import com.example.shaadicloneapp.data.repository.dataSource.UserLocalDataSource
import com.example.shaadicloneapp.data.repository.dataSource.UserRemoteDataSource
import com.example.shaadicloneapp.data.repository.dataSource.UserRepositoryImpl
import com.example.shaadicloneapp.data.repository.dataSourceImpl.UserLocalDataSourceImpl
import com.example.shaadicloneapp.data.repository.dataSourceImpl.UserRemoteDataSourceImpl
import com.example.shaadicloneapp.domain.repository.UserRepository
import com.example.shaadicloneapp.domain.use_cases.*
import com.example.shaadicloneapp.presentation.viewModel.UserViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(

        @ApplicationContext
        context: Context

    ): Retrofit {

        return Retrofit.Builder()
            .baseUrl("https://randomuser.me/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(ApiClient.makeOkHttpClient())
            .build()
    }

    private fun makeLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }
    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideNewsDatabase(app: Application): UserDatabase {
        return Room.databaseBuilder(app, UserDatabase::class.java, "user_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideUserDao(userDatabase: UserDatabase): UserDao {
        return userDatabase.getUserDao()
    }

    @Singleton
    @Provides
    fun provideLocalDataSource(userDao: UserDao):UserLocalDataSource{
        return UserLocalDataSourceImpl(userDao)
    }

    @Singleton
    @Provides
    fun provideNewsRemoteDataSource(
        apiService: ApiService
    ):UserRemoteDataSource{
        return UserRemoteDataSourceImpl(apiService)
    }

    @Singleton
    @Provides
    fun provideNewsRepository(
        userRemoteDataSource: UserRemoteDataSource,
        userLocalDataSource: UserLocalDataSource
    ): UserRepository {
        return UserRepositoryImpl(
            userLocalDataSource,
            userRemoteDataSource
        )
    }

    @Singleton
    @Provides
    fun provideUserUseCases(
        userRepository: UserRepository
    ): UserUseCasesWrapper {
        return UserUseCasesWrapper(
            getSavedUsersUseCase = GetSavedUsersUseCase(userRepository),
            getUsersUseCase = GetUsersUseCase(userRepository),
            saveUsersUseCase = SaveUsersUseCase(userRepository),
            deleteSavedUserUseCase = DeleteSavedUserUseCase(userRepository),
            deleteAllUseCase = DeleteAllUseCase(userRepository),
            updateAcceptedFieldUseCase = UpdateAcceptedFieldUseCase(userRepository),
            updateDeclineFieldUseCase = UpdateDeclineFieldUseCase(userRepository)
        )
    }

    @Singleton
    @Provides
    fun provideUserViewModelFactory(
        application: Application,
        userUseCasesWrapper: UserUseCasesWrapper
    ):UserViewModelFactory{
        return UserViewModelFactory(
            application,
            userUseCasesWrapper
        )
    }

}