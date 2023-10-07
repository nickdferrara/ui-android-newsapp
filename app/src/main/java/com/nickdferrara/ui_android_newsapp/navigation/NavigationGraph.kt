package com.nickdferrara.ui_android_newsapp.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.nickdferrara.ui_android_newsapp.screen.article.ArticleScreen
import com.nickdferrara.ui_android_newsapp.screen.home.HomeScreen

@Composable
fun NavigationGraph(
    navController: NavHostController,
    innerPadding: PaddingValues
) {
    NavHost(navController,
        startDestination = Screen.Home.route,
        Modifier.padding(innerPadding)) {
        composable(Screen.Home.route) { HomeScreen(navController = navController) }
        composable(
            route = Screen.Article.route,
            arguments = listOf(
                navArgument(name = "articleUrl") {
                    type = NavType.StringType
                }
            )
        ) {
            val articleUrl = remember {
                it.arguments?.getString("articleUrl")
            }

            ArticleScreen(navController = navController, articleUrl = articleUrl!!)
        }
    }
}