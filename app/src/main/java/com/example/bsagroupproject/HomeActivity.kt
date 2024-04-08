package com.example.bsagroupproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.compose.rememberNavController
import com.example.bsagroupproject.components.BottomNav
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
                   Scaffold(
                       topBar = {
                           TopAppBar(
                               title = { Text(text = "Home") }
                           )
                       },
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
                                   title = "Chat",
                                   route = "Chat",
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
                           //nav controller
                           val navController= rememberNavController()
                           BottomNav(items = bottomBarItems, navController = navController,
                               onItemClicked = {item ->
                                   when(item.route){
                                       "Home"-> {
                                           // Navigate to Home screen
                                           navController.navigate("home")
                                       }
                                       "Chat" -> {
                                           navController.navigate("chat")
                                       }
                                       "Appoint" -> {
                                           navController.navigate("appoint")
                                       }
                                   }
                               }
                           )
                       }
                   ) { innerPadding->
                       Column(modifier = Modifier.fillMaxSize(),
                           horizontalAlignment = Alignment.CenterHorizontally,
                           verticalArrangement = Arrangement.Center
                           ) {
                           Text(text = "Hey This home activity ")

                       }
                       
                   }

                   
               }
           }



       }
        
    }
}

