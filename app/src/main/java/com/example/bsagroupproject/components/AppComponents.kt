package com.example.bsagroupproject.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bsagroupproject.R
import com.example.bsagroupproject.ui.theme.Primary
import com.example.bsagroupproject.ui.theme.PurpleGrey80
import com.example.bsagroupproject.ui.theme.Secondary
import com.example.bsagroupproject.ui.theme.TextColor



@Composable
fun NormalText(value: String){
    Text(
        text =value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(),
        style = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal
        ),
        color= colorResource(id = R.color.colorText ),
        textAlign = TextAlign.Center
      )
}

@Composable
fun NormalTextHeading(value: String){
    Text(
        text =value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(),
        style = TextStyle(
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal
        ),
        color= colorResource(id = R.color.colorText ),
        textAlign = TextAlign.Center


    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTextField(labelValue:String,imageVector: ImageVector){

    val textValue= remember {
        mutableStateOf("") }


    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(
                RoundedCornerShape(
                    topStart = 16.dp,
                    topEnd = 16.dp,
                    bottomStart = 16.dp,
                    bottomEnd = 16.dp
                )
            ),
        label = {Text (text = labelValue)},
        colors =TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = PurpleGrey80,
            unfocusedBorderColor = PurpleGrey80,
            cursorColor = PurpleGrey80

        ) ,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        value = textValue.value,
        onValueChange ={ newValue->
                textValue.value=newValue
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Icon"
            )
        },
        singleLine = true ,
        maxLines = 1
        )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyPasswordField(labelValue:String,imageVector: ImageVector){

    val password= remember {
        mutableStateOf("") }

    val passwordVisible= remember {
        mutableStateOf(false)
    }


    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(
                RoundedCornerShape(
                    topStart = 16.dp,
                    topEnd = 16.dp,
                    bottomStart = 16.dp,
                    bottomEnd = 16.dp
                )
            ),
        label = {Text (text = labelValue)},
        colors =TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = PurpleGrey80,
            unfocusedBorderColor = PurpleGrey80,
            cursorColor = PurpleGrey80

        ) ,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        value = password.value,
        onValueChange ={ newValue->
            password.value=newValue
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Icon"
            )
        },
        trailingIcon = {
           val iconImage=if ( passwordVisible.value) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
            var description=if(passwordVisible.value){
                stringResource(id = R.string.hide_password)
            }else{
                stringResource(id = R.string.show_password)
            }
                IconButton(onClick = { passwordVisible.value = !passwordVisible.value}) {
                    Icon(imageVector=iconImage, contentDescription =description )
                }

        },
        visualTransformation = if(passwordVisible.value) VisualTransformation.None else
                      PasswordVisualTransformation()
    )
}

@Composable
fun checkBoxComponent(value: String){
Row(modifier = Modifier
    .fillMaxWidth()
    .heightIn(56.dp)
    .padding(16.dp),
    verticalAlignment = Alignment.CenterVertically

) {
    val checkedState= remember {
        mutableStateOf(false)
    }
      Checkbox(
          checked =checkedState.value ,
          onCheckedChange ={
          checkedState.value=it
      } )
    
    Text(modifier = Modifier
        .fillMaxWidth()
        .heightIn(),
        style = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal
        ),
        color= colorResource(id = R.color.colorText ),
        text = "By continuing you accpet out Privacy Policy and Term of use")
     
}
}

@Composable
fun ButtonComponent(value:String){
    Button(onClick = { /*TODO*/ },
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(48.dp),
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(Color.Transparent)
        ) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .heightIn(48.dp)
            .background(
                brush = Brush.horizontalGradient(listOf(Primary, Secondary)),
                shape = RoundedCornerShape(50.dp)
            ),
            contentAlignment = Alignment.Center
            ){
            Text(text = value,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
                )
        }
        

    }
}

@Composable
fun DividerTextComponents(){
    Row (modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
        ){
        Divider(modifier = Modifier
            .fillMaxWidth()
            .weight(1f),
            color = Color.Gray,
            thickness = 1.dp
        )
          Text(
              modifier = Modifier.padding(8.dp),
              text = "Or",
              fontSize = 18.sp,
              color = TextColor
          )

        Divider(modifier = Modifier
            .fillMaxWidth()
            .weight(1f),
            color = Color.Gray,
            thickness = 1.dp
        )

    }
}

@Composable
fun ClickableLoginTextComponent(tryingToLogin:Boolean=true,onTextSelected: (String) -> Unit) {
    val initialText = if(tryingToLogin) "Already have an account !" else " Don't have an account yet ? "
    val logInText = if (tryingToLogin) "LogIn" else "Register here"

    val annotatedString = buildAnnotatedString {
        append(initialText)
        withStyle(style = SpanStyle(color = Primary)) {
            pushStringAnnotation(tag = logInText, annotation = logInText)
            append(logInText)
        }
    }

    ClickableText(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 30.dp),
        style = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal,
            textAlign = TextAlign.Center
        ),
        text = annotatedString, onClick = { offset ->
            annotatedString.getStringAnnotations(offset, offset)
                .firstOrNull()?.also { span ->
                    Log.d("ClickableTextComponent", "${span.item} ")
                    if (span.item == logInText) {
                        onTextSelected(span.item)
                    }
                }
        }
    )
}


@Composable
fun UnderLinedText(value: String){
    Text(
        text =value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(),
        style = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal
        ),
        color= colorResource(id = R.color.colorText ),
        textAlign = TextAlign.Center,
        textDecoration = TextDecoration.Underline
    )
}