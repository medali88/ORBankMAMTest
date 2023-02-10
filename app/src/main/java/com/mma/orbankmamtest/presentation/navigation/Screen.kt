package com.mma.orbankmamtest.presentation.navigation

interface Screen {
    val route: String
}

interface NavScreen : Screen {
    val stringId: Int
    val drawableId: Int
}
