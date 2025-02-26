package com.jam.mvvmdemojaiminsarvan.data.repository

import com.jam.mvvmdemojaiminsarvan.data.api.NetworkService
import com.jam.mvvmdemojaiminsarvan.data.models.Article
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TopHeadlineRepository @Inject constructor(private val networkService: NetworkService) {

    fun getTopHeadlines(country: String): Flow<List<Article>>{
        return flow {
            emit(networkService.getTopHeadlines(country))
        }.map {
            it.articles
        }

    }

}