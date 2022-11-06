package com.example.shaadicloneapp.data.api

import com.example.shaadicloneapp.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {

    fun makeOkHttpClient(): OkHttpClient{

        return OkHttpClient.Builder()
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .writeTimeout(90, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .apply {
                networkInterceptors().add(Interceptor { chain->
                    val original = chain.request()
                    val request = original.newBuilder()
                        .method(original.method, original.body)
                        .build()
                    return@Interceptor chain.proceed(request)
                })
                if (BuildConfig.DEBUG) addInterceptor(makeLoggingInterceptor())
            }.build()
    }

    private fun makeLoggingInterceptor(): HttpLoggingInterceptor {

        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    }

}