package com.nickdferrara.ui_android_newsapp.data.remote

import com.nickdferrara.ui_android_newsapp.data.remote.responses.ArticleList
import retrofit2.http.GET
import retrofit2.http.Query

interface NewYorkTimesApi {
    @GET("home.json/api-key=$API_KEY")
    suspend fun getArticleList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): ArticleList

    companion object {
        const val API_KEY = "*"
    }
}