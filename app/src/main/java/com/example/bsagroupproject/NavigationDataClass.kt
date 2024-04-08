package com.example.bsagroupproject

import androidx.compose.ui.graphics.painter.Painter


//data class for bottom navigation
//calling from App Component
data class BottomNavigationItem(
    val title:String,
    val route:String,
    val selectedIcon:Painter,
    val unselectedIcon:Painter
)
