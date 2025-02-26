package com.jam.mvvmdemojaiminsarvan.di

import android.content.Context
import android.util.Log
import com.jam.mvvmdemojaiminsarvan.MVVMApplication
import com.jam.mvvmdemojaiminsarvan.data.api.NetworkService
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class ApplicationModule(private val application: MVVMApplication) {

    @ApplicationContext
    @Provides
    fun provideContext(): Context{
        return application
    }

    @BaseUrl
    @Provides
    fun provideBaseUrl(): String = "https://newsapi.org/v2/"

    @Singleton
    @Provides
    fun provideGsonCoverterFactory(): GsonConverterFactory = GsonConverterFactory.create()


    @Singleton
    @Provides
    fun provideNetworkService(@BaseUrl baseUrl: String, gsonConverterFactory: GsonConverterFactory): NetworkService{
        Log.e("baseUrl", baseUrl)
        val loggingInterceptor = Interceptor { chain ->
            val request = chain.request()
            println("Request Headers: ${request.headers} ${request.url}")

            val response = chain.proceed(request.newBuilder().header("User-Agent","PostmanRuntime/7.37.3").build())

            println("Response Headers: ${response.headers}")

            response
        }
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient)
            .build()
            .create(NetworkService::class.java)
    }

}