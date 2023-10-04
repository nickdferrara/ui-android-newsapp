package com.nickdferrara.ui_android_newsapp.screen.article

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nickdferrara.ui_android_newsapp.data.models.ArticleListEntry
import com.nickdferrara.ui_android_newsapp.repository.ArticleRepository
import com.nickdferrara.ui_android_newsapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleScreenViewModel @Inject constructor(
): ViewModel() {
}