package com.example.bsagroupproject.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachFile
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.bsagroupproject.ChatOperations
import com.example.bsagroupproject.R
import com.example.bsagroupproject.components.IconComponentImageVector
import com.example.bsagroupproject.data.Message
import com.example.bsagroupproject.data.Person

import com.example.bsagroupproject.model.ChatViewModel
import com.google.firebase.auth.FirebaseAuth
import androidx.compose.runtime.LaunchedEffect as LaunchedEffect1


@Composable
fun ChatScreen(navHostController: NavHostController, chatViewModel: ChatViewModel) {
    var message by remember {
        mutableStateOf(" ")
    }
    val data by chatViewModel.selectedPersonProfile.collectAsState()
    val messageList by chatViewModel.messageList.collectAsState()
    val context = LocalContext.current
    val chatId by chatViewModel.chatId.collectAsState()

    LaunchedEffect (data){
        Log.d("data","current_Profile_change")
        chatViewModel.getChatNode {
            //chatViewModel.setChatId(data.userID)
            Log.d("chat_id",chatId.toString())
        }

    }
    LaunchedEffect(chatId,messageList) {
        // Fetch messages when the screen is shown
        chatViewModel.getMessages(chatId)
        Log.d("messages", messageList.toString())
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)

    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxSize()
                .padding(start = 8.dp, end = 8.dp, top = 8.dp, bottom = 0.dp)
        ) {
            UserNameRow(
                person = data,
                modifier = Modifier.padding(top = 60.dp, start = 20.dp, end = 20.dp, bottom = 20.dp)
            )
            Box(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .fillMaxSize()
                    .background(
                        Color.White, RoundedCornerShape(
                            topStart = 30.dp, topEnd = 30.dp
                        )
                    )
                    .padding(top = 25.dp)

            ) {
                LazyColumn(
                    modifier = Modifier.padding(
                        start = 15.dp,
                        top = 25.dp,
                        end = 15.dp,
                        bottom = 75.dp
                    ),
                    reverseLayout = true,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(messageList.reversed()) { message ->
                        ChatRow(message)
                    }
                }
            }
        }

        CustomTextField(
            text = message,
            chatViewModel,
            onValueChange = { message = it },
            onSendClick = {
                if (message.isNotEmpty()) {
                    //     ChatOperations().sendMessage(message, data.userID)
                    chatViewModel.sendMessageCall(message)
                    message = " " //clear the message after sending
                    Toast.makeText(context, "message sent", Toast.LENGTH_SHORT).show()
//                    chatViewModel.getChatNode {
//                        chatViewModel.getMessages(chatId)
//                    }
                }

            },
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 20.dp)
                .align(BottomCenter)
        )

    }
}


@Composable
fun ChatRow(message: Message) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = if (message.senderUID == FirebaseAuth.getInstance().currentUser?.uid) Alignment.Start else Alignment.End
    ) {
        Box(
            modifier = Modifier
                .background(
                    if (message.senderUID == FirebaseAuth.getInstance().currentUser?.uid) Red else Yellow,
                    RoundedCornerShape(100.dp)
                ),
            contentAlignment = if (message.senderUID == FirebaseAuth.getInstance().currentUser?.uid) Alignment.TopStart else Alignment.TopEnd
        ) {
            Text(
                text = message.content,
                style = TextStyle(color = Color.Black, fontSize = 15.sp),
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 15.dp),
                textAlign = if (message.senderUID == FirebaseAuth.getInstance().currentUser?.uid) TextAlign.Start else TextAlign.End
            )
        }
    }
}


@Composable
fun CommonIconButton(imageVector: ImageVector) {
    Box(
        modifier = Modifier
            .background(Yellow, CircleShape)
            .size(33.dp), contentAlignment = Alignment.Center
    ) {
        IconComponentImageVector(icon = imageVector, size = 15.dp, tint = Color.Black)
    }

}

@Composable
fun CommonIconButtonDrawable(
    message: String,
    chatViewModel: ChatViewModel,
    imageVector: ImageVector,
    onSendClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .background(Color.Yellow, CircleShape)
            .size(33.dp)
            .clickable {
                onSendClick()
            },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector, contentDescription = "",
            tint = Color.Black,
            modifier = Modifier.size(15.dp)
        )
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(
    text: String,
    chatViewModel: ChatViewModel,
    modifier: Modifier = Modifier,
    //Lambda Function
    onValueChange: (String) -> Unit,
    onSendClick: () -> Unit
) {
    TextField(
        value = text,
        onValueChange = {
            onValueChange(it)
        },
        placeholder = {
            Text(
                text = stringResource(id = R.string.type_message),
                style = TextStyle(
                    fontSize = 14.sp,
                    color = Black
                ),
                textAlign = TextAlign.Center
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Gray,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent
        ),
        leadingIcon = { CommonIconButton(imageVector = Icons.Default.AttachFile) },
        trailingIcon = {
            CommonIconButtonDrawable(
                message = text,
                imageVector = Icons.Filled.Send,
                onSendClick = {
                    onSendClick()
                },
                chatViewModel = chatViewModel
            )
        },
        modifier = modifier.fillMaxWidth(),
        shape = CircleShape
    )
}


@Composable
fun UserNameRow(
    modifier: Modifier = Modifier,
    person: Person
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row {
            Icon(imageVector = Icons.Filled.Person, contentDescription = "userImage")
            Spacer(modifier = Modifier.width(8.dp))
            Column {

                Text(
                    text = person.userName,
                    style = TextStyle(
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                )

                Text(
                    text = stringResource(id = R.string.online),
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 14.sp
                    )
                )
            }

        }
        IconComponentImageVector(icon = Icons.Default.MoreVert, size = 24.dp, tint = Color.White)
    }

}

@Preview
@Composable
private fun PreviewChatScreen() {
    val navController = rememberNavController()
    ChatScreen(navController, chatViewModel = viewModel())
}



