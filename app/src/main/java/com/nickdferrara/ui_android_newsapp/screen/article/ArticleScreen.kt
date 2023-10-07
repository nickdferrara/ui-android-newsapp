package com.nickdferrara.ui_android_newsapp.screen.article

import android.graphics.Bitmap
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun ArticleScreen(
    navController: NavController,
    articleUrl: String,
    viewModel: ArticleScreenViewModel = hiltViewModel(),
) {
    var backEnabled by remember {
        mutableStateOf(false)
    }

    var webView: WebView? = null

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        AndroidView(
            factory = {context ->
                WebView(context).apply {
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                    webViewClient = object: WebViewClient() {
                        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                            backEnabled = view!!.canGoBack()
                        }
                    }
                    settings.javaScriptEnabled = true
                    loadUrl(articleUrl)
                    webView = this
                }

            },
            update = {
                webView = it
            }
        )

        BackHandler(enabled = backEnabled) {
            webView?.goBack()
        }
    }


}