package com.nickdferrara.ui_android_newsapp.screen.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nickdferrara.ui_android_newsapp.data.models.ArticleListEntry
import com.nickdferrara.ui_android_newsapp.repository.ArticleRepository
import com.nickdferrara.ui_android_newsapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val repository: ArticleRepository
): ViewModel() {

    var articleList = mutableStateOf<List<ArticleListEntry>>(listOf())

    init {
        loadTopStories()
    }

    fun loadTopStories() {
        viewModelScope.launch {

            when(val result = repository.getArticleList()) {
                is Resource.Success -> {
                    val articles = result.data!!.results.map { entry ->
                        ArticleListEntry(
                            articleTitle = entry.title,
                            articleSubTitle = entry.abstract,
                            author = entry.byline,
                            articleUri = entry.uri,
                            articleUrl = entry.url,
                            articleImage = entry.multimedia[0].url
                        )
                    }
                    articleList.value += articles
                }

                is Resource.Error -> {}
                is Resource.Loading -> {}
            }
        }
    }

    fun refreshTopStories() {
        loadTopStories()
    }

}

