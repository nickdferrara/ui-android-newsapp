package com.nickdferrara.ui_android_newsapp.navigation

sealed class Screen(val route: String, var title: String) {
    object Home: Screen("home_screen", "Home")
    object Article: Screen("article_screen", "Article Detail")
}
