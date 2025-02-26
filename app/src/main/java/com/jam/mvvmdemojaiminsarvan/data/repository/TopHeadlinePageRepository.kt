package com.jam.mvvmdemojaiminsarvan.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.jam.mvvmdemojaiminsarvan.data.api.NetworkService
import com.jam.mvvmdemojaiminsarvan.data.models.Article
import com.jam.mvvmdemojaiminsarvan.data.models.RemoteKey
import com.jam.mvvmdemojaiminsarvan.data.topHeadlinePagingSource
import com.jam.mvvmdemojaiminsarvan.db.ArticleDAO
import com.jam.mvvmdemojaiminsarvan.db.RemoteKeyDAO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TopHeadlinePageRepository @Inject constructor(private val networkService: NetworkService,private val articleDAO: ArticleDAO, private val remoteKeyDAO: RemoteKeyDAO) {

    @OptIn(ExperimentalPagingApi::class)
    fun getTopHealinePage(): Flow<PagingData<Article>> =

        Pager(config = PagingConfig(pageSize = 10, enablePlaceholders = false),
            remoteMediator = topHeadlinePagingSource(networkService,articleDAO,remoteKeyDAO),
            pagingSourceFactory = {
                articleDAO.getAllArticles() }
        ).flow

   suspend fun deleteTopHealinePage(){
        withContext(Dispatchers.IO) {
            articleDAO.clearAllArticles()
        }
    }
}