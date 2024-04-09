package com.example.bsagroupproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.rememberNavController
import com.example.bsagroupproject.components.BottomNav
import com.example.bsagroupproject.components.NavigationForHome
import com.example.bsagroupproject.data.BottomNavigationItem
import com.example.bsagroupproject.ui.theme.BsagroupprojectTheme

class HomeActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       setContent {
           BsagroupprojectTheme {
               Surface (color = Color.White,
                   modifier =Modifier.fillMaxSize()
                   ){
                   //nav controller
                   val navController= rememberNavController()
                   Scaffold(

                       bottomBar = {
                           //Items in bottom navigation Bar
                           val bottomBarItems= listOf(
                               BottomNavigationItem(
                                   title = "Home",
                                   route = "Home",
                                   selectedIcon = painterResource(id = R.drawable.filled_home),
                                   unselectedIcon = painterResource(id = R.drawable.outline_home)
                               ),
                               BottomNavigationItem(
                                   title = "Chat_Home",
                                   route = "Chat_Home",
                                   selectedIcon = painterResource(id = R.drawable.chat_bubble_outline_badged),
                                   unselectedIcon = painterResource(id = R.drawable.comment_circle_chat_message)
                               ),
                               BottomNavigationItem(
                                   title = "Appoint",
                                   route = "Appoint",
                                   selectedIcon = painterResource(id = R.drawable.outline_profile),
                                   unselectedIcon = painterResource(id = R.drawable.filled_profile)
                               )
                           )

                           BottomNav(items = bottomBarItems, navController = navController,
                               onItemClicked = {item ->
                                   when(item.route){
                                       "Home"-> {
                                           // Navigate to Home screen
                                           navController.navigate("home")
                                       }
                                       "Chat_Home" -> {
                                           navController.navigate("chat_home")
                                       }
                                       "Appoint" -> {
                                           navController.navigate("appoint")
                                       }
                                       "Chat_Screen" ->{
                                           navController.navigate("chat_screen")
                                       }
                                   }
                               }
                           )
                       }
                   ) { innerPadding->
                       Surface(
                           modifier = Modifier
                               .padding(innerPadding)
                               .fillMaxSize()
                       ) {
                           NavigationForHome(
                               navHostController = navController,
                               )
                       }
//                       Column(modifier = Modifier.fillMaxSize()
//                           .padding(innerPadding),
//                           horizontalAlignment = Alignment.CenterHorizontally,
//                           verticalArrangement = Arrangement.Center
//                           ) {
//                           Text(text = "Hey This home activity ")

//                       }
                   }
               }
           }
       }
        
    }
}

