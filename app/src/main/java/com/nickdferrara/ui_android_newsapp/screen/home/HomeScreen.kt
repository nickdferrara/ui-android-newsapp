package com.nickdferrara.ui_android_newsapp.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.PullRefreshState
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.nickdferrara.ui_android_newsapp.data.models.ArticleListEntry
import java.net.URLEncoder
import java.nio.charset.StandardCharsets


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    navController: NavController,
    homeScreenViewModel: HomeScreenViewModel = hiltViewModel()
) {

    val articleList by remember {
        homeScreenViewModel.articleList
    }

    val refreshing by remember {
        homeScreenViewModel.isRefreshing
    }

    val lifecycleEvent = rememberLifecycleEvent()
    val pullRefreshState = rememberPullRefreshState(
        refreshing,
        { homeScreenViewModel.refreshTopStories() }
    )

    LaunchedEffect(lifecycleEvent) {
        if (lifecycleEvent == Lifecycle.Event.ON_RESUME) {
            homeScreenViewModel.refreshTopStories()
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        if (articleList.isEmpty()) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(65.dp)
                    .scale(0.2f)
            )
        } else {
            DisplayHomeScreenContent(
                navController = navController,
                articleList = articleList,
                pullRefreshState = pullRefreshState,
                refreshing = refreshing
            )
        }
    }
}

@Composable
private fun rememberLifecycleEvent(
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
): Lifecycle.Event {

    var state by remember {
        mutableStateOf(Lifecycle.Event.ON_ANY)
    }

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver{_, event ->
            state = event
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    return state
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DisplayHomeScreenContent(
    navController: NavController,
    articleList: List<ArticleListEntry>,
    pullRefreshState: PullRefreshState,
    refreshing: Boolean
) {
    ArticleColumn(
        navController = navController,
        articleList = articleList,
        pullRefreshState = pullRefreshState,
        refreshing = refreshing
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ArticleColumn(
    navController: NavController,
    articleList: List<ArticleListEntry>,
    pullRefreshState: PullRefreshState,
    refreshing: Boolean
) {
    Box(Modifier.pullRefresh(pullRefreshState)) {
        LazyColumn() {
            items(articleList) {article ->
                ArticleItem(
                    navController = navController,
                    article = article
                )
            }
        }
        PullRefreshIndicator(
            refreshing,
            pullRefreshState,
            Modifier
                .align(Alignment.Center)
        )
    }
}

@Composable
fun ArticleItem(
    navController: NavController,
    article: ArticleListEntry
) {
    Column(
        modifier = Modifier
            .padding(top = 16.dp)
            .clickable {
                val encodedUrl = URLEncoder.encode(
                    article.articleUrl,
                    StandardCharsets.UTF_8.toString()
                )
                navController.navigate(
                    route = "article_screen/${encodedUrl}"
                )
            }
    ) {
        Text(
            text = article.articleTitle,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .padding(start = 10.dp, end = 10.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = article.articleSubTitle,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.secondary,
            modifier = Modifier
                .padding(start = 10.dp, end = 10.dp)

        )
        Spacer(modifier = Modifier.height(20.dp))
        SubcomposeAsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(article.articleImage)
                .crossfade(true)
                .build(),
            contentDescription = "${article.articleImage} image",
            modifier = Modifier//
        )
        Spacer(modifier = Modifier.height(20.dp))
    }
}