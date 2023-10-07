package com.nickdferrara.ui_android_newsapp.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun HomeScreen(
    navController: NavController,
    homeScreenViewModel: HomeScreenViewModel = hiltViewModel()
) {
    val articleList by remember {
        homeScreenViewModel.articleList
    }

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn() {
            items(articleList) {article ->

                Column(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .clickable {
                            val encodedUrl = URLEncoder.encode(article.articleUrl,
                                StandardCharsets.UTF_8.toString())
                            navController.navigate(
                                route ="article_screen/${encodedUrl}"
                            )
                        }
                ) {
                    Text(
                        text = article.articleTitle,
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 26.sp,
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier
                            .padding(start = 10.dp, end = 10.dp)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = article.articleSubTitle,
                        color = Color.Gray,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier
                            .padding(start = 10.dp, end = 10.dp)

                    )

                    Spacer(modifier = Modifier.height(20.dp))

//                    SubcomposeAsyncImage(
//                        model = ImageRequest.Builder(LocalContext.current)
//                            .data(article.articleImage)
//                            .crossfade(true)
//                            .build(),
//                        contentDescription = "${article.articleImage} image",
//                        modifier = Modifier
//
//                    )

                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
        }
    }
}