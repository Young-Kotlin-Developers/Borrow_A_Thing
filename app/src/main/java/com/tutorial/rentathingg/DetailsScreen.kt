package com.tutorial.rentathingg

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.accompanist.coil.rememberCoilPainter
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding

@Composable
fun DetailsScreen(navController: NavController, itemViewModel: ItemViewModel, number: Int?) {
    var title by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var imageUrl by remember { mutableStateOf("") }
    var details by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var uploadDate by remember { mutableStateOf("") }

    val myItem = listOf(itemViewModel.books.value[number!!])

    Log.d("myItem", myItem.toString())

    Scaffold(backgroundColor = Color.White) {
        LazyColumn() {
            itemsIndexed(myItem) {position, data->
                Photoadapter(navController, data.imageUri)
                Infoproduct(data.uploadDate, data.title, data.price)
            }
            itemsIndexed(myItem) { position, data ->
                TextContent(data)
            }
            item {
                BottomNav()
            }
        }
    }
}

data class TextData(val title: String, val detail: String)

@Composable
fun Photoadapter(navController: NavController, imageUrl: String) {

    Box() {
        Image(
            painter = rememberCoilPainter(request = imageUrl),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        )
        Box(
            modifier = Modifier
                .statusBarsPadding()
                .fillMaxWidth()
        ) {
            TopButton(
                imageVector = Icons.Default.ArrowBack,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(16.dp)
            ) {
                navController.popBackStack()
            }
            TopButton(
                imageVector = Icons.Default.BookmarkBorder,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(16.dp)
            ) {

            }
        }
    }
}

@Composable
fun Infoproduct(uploadDate: String, title: String, price: String) {
    Divider(
        color = Color(0xFFECECEE),
        modifier = Modifier.padding(0.dp)
    )
    Column(
        Modifier
            .fillMaxSize()
            .padding(15.dp)
    ) {
        Text(
            text = uploadDate,
            fontWeight = FontWeight.Light,
            fontSize = 10.sp
        )
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
        Text(
            text = "$price zł",
            fontWeight = FontWeight.Normal,
            fontSize = 18.sp
        )
        Divider(
            color = Color(0xFFECECEE),
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Composable
fun TextContent(data: ItemResult) {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(
            text = data.details.uppercase(),
            fontSize = 14.sp,
            fontWeight = FontWeight.ExtraBold,
            letterSpacing = 0.75.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = data.description,
            fontSize = 14.sp,
            fontWeight = FontWeight.Light,
            lineHeight = 18.sp
        )
    }
}

@Composable
fun TopButton(imageVector: ImageVector, modifier: Modifier, clickListener: () -> Unit) {
    Button(
        onClick = { clickListener() },
        border = BorderStroke(2.dp, Color(0xDDF6F9FF)),
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(0xDDF6F9FF),
            contentColor = Color(0xff6162F5)
        ),
        modifier = modifier.size(48.dp)
    ) {
        Icon(imageVector = imageVector, contentDescription = "")
    }
}

@Composable
fun BottomNav() {
    Column(
        Modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .padding(10.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xffEE4367),
                    contentColor = Color(0xFFFFF5EE),
                ),
                shape = RoundedCornerShape(16.dp),
            ) {
                Icon(
                    Icons.Filled.Chat,
                    contentDescription = "Email",
                    modifier = Modifier.size(ButtonDefaults.IconSize)
                )
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                Text(text = "Message")
            }
            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xffEE4367),
                    contentColor = Color(0xFFFFF5EE),
                ),
                shape = RoundedCornerShape(16.dp),
            ) {
                Icon(
                    Icons.Filled.Phone,
                    contentDescription = "Phone",
                    modifier = Modifier.size(ButtonDefaults.IconSize)
                )
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                Text(text = "Phone", color = Color.White)
            }
        }
    }
}
