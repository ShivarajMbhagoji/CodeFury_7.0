package com.hackathon.model

import androidx.compose.ui.graphics.vector.ImageVector

data class NavBarItem(
    val title: String,
    val route: String,
    val icon:ImageVector
)