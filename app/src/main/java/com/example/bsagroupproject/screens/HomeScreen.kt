package com.example.bsagroupproject.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.bsagroupproject.R
import com.example.bsagroupproject.components.ButtonComponent
import com.example.bsagroupproject.components.NormalTextHeading
import com.example.bsagroupproject.data.BottomNavigationItem
import com.example.bsagroupproject.model.ChatViewModel
import com.example.bsagroupproject.model.LoginViewModel
import com.example.bsagroupproject.navigation.Screen
import com.example.bsagroupproject.navigation.ScreenRouting
import com.example.bsagroupproject.ui.theme.BsagroupprojectTheme

@Composable
fun HomeScreen(chatViewModel: ChatViewModel) {

    val messageList by chatViewModel.messageList.collectAsState()
    val chatId by chatViewModel.chatId.collectAsState()

        chatViewModel.getChatNode{
            chatViewModel.getMessages(chatId)
            Log.d("messages",messageList.toString())
        }

    BsagroupprojectTheme {
        Surface(
            color = Color.White,
            modifier = Modifier.fillMaxSize()
        ) {
            //nav controller
            val navController = rememberNavController()
            Scaffold(

                bottomBar = {
                    //Items in bottom navigation Bar
                    val bottomBarItems = listOf(
                        BottomNavigationItem(
                            title = "Home_Screen",
                            route = "Home_Screen",
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
                        onItemClicked = { item ->
                            navController.navigate(item.route)

                        }
                    )
                }
            ) { innerPadding ->
                Surface(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize()
                ) {
                    NavigationForHome(navHostController = navController, chatViewModel)
                }

            }
        }

    }


}

//Bottom Navigation Bar for Home Screen
@Composable
fun BottomNav(
    items: List<BottomNavigationItem>,
    navController: NavController,
    onItemClicked: (BottomNavigationItem) -> Unit
) {
    NavigationBar(
        modifier = Modifier.shadow(8.dp),
        containerColor = MaterialTheme.colorScheme.onPrimary
    ) {
        //to remember back state
        val backStackEntry = navController.currentBackStackEntryAsState()

        items.forEachIndexed { index, bottomNavigationItem ->
            NavigationBarItem(
                selected = bottomNavigationItem.route == backStackEntry.value?.destination?.route,
                onClick = {
                    onItemClicked(bottomNavigationItem)
                },
                label = {
                    if (bottomNavigationItem.route == backStackEntry.value?.destination?.route) {
                        Text(
                            text = bottomNavigationItem.title,
                            fontWeight = FontWeight(900),
                            color = MaterialTheme.colorScheme.primary
                        )
                    } else {
                        Text(
                            text = bottomNavigationItem.title,
                            fontWeight = FontWeight(700),
                            color = Color.Gray
                        )
                    }
                },
                alwaysShowLabel = true,
                icon = {
                    if (bottomNavigationItem.route == backStackEntry.value?.destination?.route) {
                        Icon(
                            bottomNavigationItem.selectedIcon,
                            contentDescription = bottomNavigationItem.title,
                            tint = MaterialTheme.colorScheme.secondary
                        )
                    } else {
                        Icon(
                            painter = bottomNavigationItem.unselectedIcon,
                            contentDescription = bottomNavigationItem.title,
                            tint = MaterialTheme.colorScheme.secondary
                        )
                    }
                }
            )
        }

    }

}

@Composable
fun NavigationForHome(
    navHostController: NavHostController, chatViewModel: ChatViewModel
) {

    NavHost(navController = navHostController, startDestination = "Home_Screen") {
        composable("Home_Screen") {
            Home()
        }

        composable("Chat_Home") {
            ChatHomeScreen(navHostController = navHostController, chatViewModel = chatViewModel)

        }

        composable("Appoint") {
            AppointScreen()
        }

        composable("Chat_Screen") {
            ChatScreen(navHostController = navHostController, chatViewModel)
        }

    }
}