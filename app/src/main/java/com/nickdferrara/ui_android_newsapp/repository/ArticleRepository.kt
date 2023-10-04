package com.nickdferrara.ui_android_newsapp.repository

import com.nickdferrara.ui_android_newsapp.data.remote.NewYorkTimesApi
import com.nickdferrara.ui_android_newsapp.data.remote.responses.ArticleList
import com.nickdferrara.ui_android_newsapp.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class ArticleRepository  @Inject constructor(
    private val api: NewYorkTimesApi
) {

    suspend fun getArticleList(): Resource<ArticleList> {
        val response = try {
            api.getArticleList()
        } catch (e: Exception) {
            return Resource.Error("An unknown error occurred. $e")
        }
        return Resource.Success(response)
    }
}