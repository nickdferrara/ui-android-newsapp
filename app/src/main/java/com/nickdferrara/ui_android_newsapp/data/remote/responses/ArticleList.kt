package com.nickdferrara.ui_android_newsapp.data.remote.responses

data class ArticleList(
    val copyright: String,
    val last_updated: String,
    val num_results: Int,
    val results: List<Result>,
    val section: String,
    val status: String
)