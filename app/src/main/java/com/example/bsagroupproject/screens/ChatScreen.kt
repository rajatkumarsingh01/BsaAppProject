package com.example.bsagroupproject.screens

import android.content.Context
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.Absolute.Center
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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.bsagroupproject.R
import com.example.bsagroupproject.components.IconComponentDrawable
import com.example.bsagroupproject.components.IconComponentImageVector
import com.example.bsagroupproject.data.Chat
import com.example.bsagroupproject.data.Person
import com.example.bsagroupproject.data.chatList


@Composable
fun ChatScreen(navHostController: NavHostController){
    var message by remember {
        mutableStateOf(" ") }
    val data=
        navHostController.previousBackStackEntry?.savedStateHandle?.get<Person>("data")?:Person()
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Black)

    ){
        Column(modifier = Modifier.fillMaxSize()) {
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
                    )
                ) {
                    items(chatList, key = { it.id }) {
                        ChatRow(chat = it)
                    }
                }
            }
        }

        CustomTextField(
            text = message, onValueChange = { message = it },
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 20.dp)
                .align(BottomCenter)
        )

        }
    }


    @Composable
    fun ChatRow(
        chat: Chat
    ){
        Column (
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = if(chat.direction) Alignment.Start else Alignment.End
        ){
            Box(modifier = Modifier
                .background(
                    if(chat.direction) Red else Yellow,
                    RoundedCornerShape(100.dp)
                ),
                contentAlignment = Alignment.Center

                )  {
                Text(
                    text =chat.message,
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 15.sp
                    ),
                    modifier = Modifier.padding(vertical = 8.dp , horizontal = 15.dp),
                    textAlign = TextAlign.End
                )
            }
            Text(text = chat.time,
                style = TextStyle(
                    color = Gray,
                    fontSize = 12.sp
                ),
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 15.dp),
            )
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
        @DrawableRes icon:Int
    ) {
        Box(modifier = Modifier
            .background(Color.Yellow, CircleShape)
            .size(33.dp),
            contentAlignment = Alignment.Center
        ){
            Icon(
                painter = painterResource(id = icon), contentDescription = "",
                tint = Color.Black,
                modifier = Modifier.size(15.dp)
            )
        }

    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun CustomTextField(
        text:String,
        modifier: Modifier=Modifier,
        //Lambda Function
        onValueChange:(String)->Unit
    ){
        TextField(value = text, onValueChange ={ onValueChange(it)},
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
            leadingIcon = { CommonIconButton(imageVector=Icons.Default.Add)},
            trailingIcon = { CommonIconButtonDrawable(R.drawable.filled_search)},
            modifier=modifier.fillMaxWidth(),
            shape = CircleShape
            )

    }



@Composable
fun UserNameRow(
    modifier: Modifier=Modifier,
    person: Person
) {
    Row (
        modifier=Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Row {
            IconComponentDrawable(icon = person.icon, size =42.dp )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                person.name?.let {
                    Text(text = it,
                        style = TextStyle(
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        ))
                }
                Text(text = stringResource(id = R.string.online),
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 14.sp
                    )
                )
            }

        }
        IconComponentImageVector(icon = Icons.Default.MoreVert, size = 24.dp , tint = Color.White )
    }

}

@Preview
@Composable
private fun PreviewChatScreen() {
    val navController = rememberNavController()
    ChatScreen(navController)
}



