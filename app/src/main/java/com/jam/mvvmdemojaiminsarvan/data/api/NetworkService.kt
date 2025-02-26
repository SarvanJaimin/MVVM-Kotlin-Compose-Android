package com.jam.mvvmdemojaiminsarvan.data.api

import com.jam.mvvmdemojaiminsarvan.data.models.TopHeadlinesResponse
import com.jam.mvvmdemojaiminsarvan.utils.AppConstant.API_KEY
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface NetworkService {

    @Headers("X-Api-Key: $API_KEY")
    @GET("top-headlines")
    suspend fun getTopHeadlines(@Query("country") country: String): TopHeadlinesResponse

    @Headers("X-Api-Key: $API_KEY")
    @GET("top-headlines")
    suspend fun getTopHeadlinesPaging(@Query("country") country: String,@Query("pageSize") pageSize: Int,@Query("page") page: Int): TopHeadlinesResponse

    @Headers("X-Api-Key: $API_KEY")
    @GET("everything")
    suspend fun searchTopHeadlines(@Query("q") query: String): TopHeadlinesResponse


}