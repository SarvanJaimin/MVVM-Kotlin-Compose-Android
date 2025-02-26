package com.jam.mvvmdemojaiminsarvan.data

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.jam.mvvmdemojaiminsarvan.data.api.NetworkService
import com.jam.mvvmdemojaiminsarvan.data.models.Article
import com.jam.mvvmdemojaiminsarvan.data.models.RemoteKey
import com.jam.mvvmdemojaiminsarvan.db.ArticleDAO
import com.jam.mvvmdemojaiminsarvan.db.RemoteKeyDAO
import com.jam.mvvmdemojaiminsarvan.utils.AppConstant
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class topHeadlinePagingSource @Inject constructor(private val networkService: NetworkService,private val articleDAO: ArticleDAO,private val remoteKeyDAO: RemoteKeyDAO) :
    RemoteMediator<Int, Article>() {

    /*override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        return try {
            val currentPage = params.key ?: 1
            val response = networkService.getTopHeadlinesPaging(AppConstant.COUNTRY, 10, currentPage)
            val articles = response.articles

            LoadResult.Page(
                data = articles,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (articles.isNotEmpty()) currentPage + 1 else null)
        } catch (e: Exception) {
            LoadResult.Error(e) // Handle errors
        }
    }*/
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Article>
    ): MediatorResult {
        return try {
            val page = when (loadType) {
                LoadType.REFRESH -> {
                    1
                }
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val remoteKey = remoteKeyDAO.getRemoteKey("news") ?: return MediatorResult.Success(true)
                    remoteKey.nextPage ?: return MediatorResult.Success(true)
                }
            }

            Log.e("page-==>","Page$page")
            val response = networkService.getTopHeadlinesPaging(AppConstant.COUNTRY, 10, page)
            if (loadType == LoadType.REFRESH) {
                articleDAO.clearAllArticles()
                remoteKeyDAO.clearAllRemoteKeys()
            }
            val article = response.articles

            articleDAO.addArticle(article)
            remoteKeyDAO.insertRemoteKey(RemoteKey("news", page + 1))


            MediatorResult.Success(endOfPaginationReached = article.isEmpty())
        } catch (e : Exception){
            MediatorResult.Error(e)
        }

    }
}