package com.jam.mvvmdemojaiminsarvan.di

import android.content.Context
import androidx.room.Room
import com.jam.mvvmdemojaiminsarvan.db.ArticleDB
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideArticleDB(@ApplicationContext context: Context):ArticleDB{
        return Room.databaseBuilder(context,ArticleDB::class.java,"ArticleDB").build()
    }

    @Singleton
    @Provides
    fun provideArticleDAO(provideArticleDB: ArticleDB) = provideArticleDB.getArticleDao()

    @Singleton
    @Provides
    fun provideRemoteKeyDAO(provideArticleDB: ArticleDB) = provideArticleDB.getRemoteKeyDao()

}