package com.jam.mvvmdemojaiminsarvan.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "article")
data class Article(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @SerializedName("source")
    var source: Source? = Source(),
    @SerializedName("author")
    var author: String? = "",
    @SerializedName("title")
    var title: String? = "",
    @SerializedName("description")
    var description: String? = "",
    @SerializedName("url")
    var url: String? = "",
    @SerializedName("urlToImage")
    var urlToImage: String? = "",
    @SerializedName("publishedAt")
    var publishedAt: String? = "",
    @SerializedName("content")
    var content: String? = null
)
