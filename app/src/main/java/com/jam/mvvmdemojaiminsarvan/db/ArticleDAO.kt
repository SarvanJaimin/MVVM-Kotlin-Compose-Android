package com.jam.mvvmdemojaiminsarvan.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jam.mvvmdemojaiminsarvan.data.models.Article

@Dao
interface ArticleDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addArticle(article: List<Article>)

    @Query("SELECT * FROM article")
    fun getAllArticles(): PagingSource<Int,Article>

    @Query("SELECT COUNT(*) FROM article")
    fun getArticleCount(): Int

    @Query("DELETE FROM article")
    suspend fun clearAllArticles()

}