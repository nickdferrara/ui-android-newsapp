package com.nickdferrara.ui_android_newsapp.navigation

sealed class Screen(
    val route: String,
    var title: String
) {

    object Home: Screen(
        route = "home_screen",
        title = "Home"
    )

    object Article: Screen(
        route = "article_screen",
        title = "Article Detail"
    )
}
