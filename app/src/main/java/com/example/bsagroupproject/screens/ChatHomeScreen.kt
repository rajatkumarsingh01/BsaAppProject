package com.example.bsagroupproject.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.bsagroupproject.R
import com.example.bsagroupproject.components.IconComponentDrawable
import com.example.bsagroupproject.components.IconComponentImageVector
import com.example.bsagroupproject.data.Chat
import com.example.bsagroupproject.data.Person
import com.example.bsagroupproject.data.personList


@Composable
fun ChatHomeScreen(
    navHostController: NavHostController
){

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Black)
    ){
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(top = 18.dp)
        ) {
            HeaderOrViewStory()

            Box(modifier = Modifier
                .fillMaxSize()
                .background(
                    Color.White, RoundedCornerShape(
                        topStart = 30.dp, topEnd = 30.dp
                    )
                )
            ){
                BottomSheetSwipeUp(
                    modifier= Modifier
                        .align(Alignment.TopCenter)
                        .padding(top = 15.dp)
                )
                LazyColumn(modifier = Modifier.padding(
                    bottom = 15.dp ,top=30.dp
                )) {
                    items(personList,key = {it.id}){
                        UserEachRow(person=it){
                            navHostController.currentBackStackEntry?.savedStateHandle?.set(
                                "data",
                                it
                            )

                            navHostController.navigate("Chat_Screen")
                        }
                    }
                }
            }

        }
    }

}

@Composable
fun ViewStoryLayout() {
    LazyRow(modifier = Modifier.padding(vertical = 20.dp)) {
        item {
            AddStoryLayout()
            Spacer(modifier = Modifier.width(10.dp))
        }

        items(personList, key = { it.id }) {
            UserStory(person = it)
        }
    }
}

@Composable
fun UserStory(
    person: Person, modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(end = 10.dp)
    ) {
        Box(
            modifier = Modifier
                .border(1.dp, Yellow, CircleShape)
                .background(Yellow, shape = CircleShape)
                .size(60.dp),
            contentAlignment = Alignment.Center
        ) {
            IconComponentDrawable(icon = person.icon, size = 65.dp)
        }
        Spacer(modifier = Modifier.height(8.dp))
        person.name?.let {
            Text(
                text = it, style = TextStyle(
                    color = Color.White, fontSize = 13.sp,
                ), modifier = Modifier.align(CenterHorizontally)
            )
        }

    }
}


@Composable
fun AddStoryLayout(
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
    ) {

        Box(
            modifier = Modifier
                .border(2.dp, DarkGray, shape = CircleShape)
                .background(Yellow, shape = CircleShape)
                .size(60.dp),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .size(20.dp)
                    .background(Color.Black, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                IconComponentImageVector(icon = Icons.Default.Add, size = 12.dp, tint = Yellow)
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(R.string.add_story), style = TextStyle(
                color = Color.White, fontSize = 13.sp,
            ), modifier = Modifier.align(CenterHorizontally)
        )

    }

}

@Composable
fun UserEachRow(
    person: Person,
    onClick: () -> Unit) {

    Box(modifier = Modifier
        .fillMaxWidth()
        .background(Color.White)
        .noRippleEffect { onClick() }
        .padding(horizontal = 20.dp, vertical = 5.dp)
    ){
        Column {
            Row (modifier = Modifier
                .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                IconComponentDrawable(icon = person.icon, size =50.dp )

                Spacer(modifier = Modifier.width(10.dp))
                Column {
                    person.name?.let {
                        Text(
                            text = it, style = TextStyle(
                                color = Color.Black, fontSize = 15.sp, fontWeight = FontWeight.Bold
                            )
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(
                            text = stringResource(id = R.string.last_message), style = TextStyle(
                                color = Gray, fontSize = 14.sp
                            )
                        )
                    }
                }

                Text(text = stringResource(id = R.string.time_12_23_pm),style= TextStyle(
                    color = Gray, fontSize = 12.sp
                )
                )

                
            }
            Spacer(modifier = Modifier.height(15.dp))
            Divider( modifier = Modifier.fillMaxWidth(), thickness = 1.dp , color = White)
        }
    }

}

@SuppressLint("UnrememberedMutableInteractionSource", "ModifierFactoryUnreferencedReceiver")
private fun Modifier.noRippleEffect(onClick: () -> Unit)=composed{
    clickable(
        interactionSource = MutableInteractionSource(),
        indication = null
    ){
        onClick()
    }


}


@Composable
fun BottomSheetSwipeUp(
    modifier: Modifier
) {
    Box(modifier = modifier
        .background(
            Color.Gray,
            RoundedCornerShape(90.dp)
        )
        .width(90.dp)
        .height(5.dp)
    )

}

@Composable
fun HeaderOrViewStory() {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, top = 20.dp)
    ){
        Header()
        ViewStoryLayout()
    }
}

@Composable
fun Header() {
   val annotatedString= buildAnnotatedString {
       withStyle(
           style = SpanStyle(
               color=Color.White,
               fontSize = 20.sp,
               fontWeight = FontWeight.W300
           )
       ){
           append(stringResource(R.string.jayant))
       }
   }
    Text(text = annotatedString)
}
