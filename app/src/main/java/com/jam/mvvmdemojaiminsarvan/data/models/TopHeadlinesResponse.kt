package com.jam.mvvmdemojaiminsarvan.data.models

import com.google.gson.annotations.SerializedName

data class TopHeadlinesResponse(
    @SerializedName("status")
    var status: String? = null,
    @SerializedName("totalResults")
    var totalResults: Int? = 0,
    @SerializedName("articles")
    var articles: ArrayList<Article> = ArrayList()
)
