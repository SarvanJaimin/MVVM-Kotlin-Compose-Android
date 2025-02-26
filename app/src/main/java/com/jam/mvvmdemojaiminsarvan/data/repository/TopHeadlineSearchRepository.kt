package com.jam.mvvmdemojaiminsarvan.data.repository

import com.jam.mvvmdemojaiminsarvan.data.api.NetworkService
import com.jam.mvvmdemojaiminsarvan.data.models.TopHeadlinesResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TopHeadlineSearchRepository @Inject constructor(private val networkService: NetworkService) {

     fun getTopHeadlineSearch(query : String) : Flow<TopHeadlinesResponse>{
         return flow {
             emit(networkService.searchTopHeadlines(query))
         }
     }
}