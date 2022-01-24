package com.tutorial.rentathingg

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Handler
import android.util.Log
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

import com.google.accompanist.coil.rememberCoilPainter
import com.google.accompanist.insets.navigationBarsPadding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import androidx.activity.viewModels
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat.startActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.ktx.toObjects

@SuppressLint("UnrememberedMutableState")
@Composable
fun SerchScreen(navController: NavController, viewmodel: ItemViewModel) {
    Column(
    ) {
        LazyRow(
            contentPadding = PaddingValues(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            itemsIndexed(viewmodel.books.value) { position, data ->
                RawItem(searchModel = data, navController = navController, position = position)
            }
        }
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            item {
                Text(
                    text = "autka",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(
                        top = 20.dp,
                        start = 16.dp,
                        bottom = 16.dp
                    ),
                )
            }
            itemsIndexed(viewmodel.books.value) { position, data ->
                SearchItem(searchModel = data, navController = navController, position)
            }
        }
    }
}

@Composable
fun SearchItem(searchModel: ItemResult, navController: NavController, position: Int) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        elevation =20.dp,
        shape = RoundedCornerShape(10.dp)

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Image(
                painter = rememberCoilPainter(request = searchModel.imageUri, fadeIn = true),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .clickable {
                        navController.navigate("Details/${position}")
                    }
                    .height(200.dp)
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row() {
                searchModel.price?.let {
                    Text(
                        text = it,
                        fontWeight = FontWeight.Normal,
                        fontSize = 12.sp,
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
            }

            searchModel.title?.let {
                Text(
                    text = it,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    lineHeight = 24.sp
                )
            }
        }
    }
}

@Composable
fun RawItem(navController: NavController, searchModel: ItemResult, position: Int) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .padding(4.dp),
        elevation =20.dp,
        shape = RoundedCornerShape(10.dp)

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Image(
                painter = rememberCoilPainter(request = searchModel.imageUri, fadeIn = true),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .clickable {
                        navController.navigate("Details/${position}")
                    }
                    .height(245.dp)
                    .fillMaxWidth()
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(2.dp)
            ) {
                searchModel.price?.let {
                    Text(
                        text = it,
                        fontWeight = FontWeight.Normal,
                        fontSize = 12.sp,
                    )
                }
                searchModel.title?.let {
                    Text(
                        text = it,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp,
                        lineHeight = 24.sp
                    )
                }
            }
        }
    }
}
