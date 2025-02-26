package com.jam.mvvmdemojaiminsarvan.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jam.mvvmdemojaiminsarvan.data.models.Article
import com.jam.mvvmdemojaiminsarvan.data.models.RemoteKey

@Database(entities = [Article::class,RemoteKey::class], version = 2)
@TypeConverters(SourceTypeConverter::class)
abstract class ArticleDB : RoomDatabase() {
    abstract fun getArticleDao(): ArticleDAO
    abstract fun getRemoteKeyDao(): RemoteKeyDAO

}