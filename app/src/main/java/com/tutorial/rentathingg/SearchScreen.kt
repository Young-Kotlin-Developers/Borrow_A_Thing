package com.tutorial.rentathingg

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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

@Composable
fun SerchScreen(navController: NavController) {

    Column(
    ) {
        LazyRow(
            contentPadding = PaddingValues(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            itemsIndexed(Textdownload) { position, data ->
                RawItem(searchModel = data, navController = navController)
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

            itemsIndexed(Textdownload) { position, data ->
                SearchItem(searchModel = data, navController = navController)
            }

            item {
                Spacer(modifier = Modifier.navigationBarsPadding())
            }
        }
    }
}

data class SearchModel(
    val image: String,
    val price: String,
    val name: String,
)


@Composable
fun SearchItem(searchModel: SearchModel, navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {

        Image(
            painter = rememberCoilPainter(request = searchModel.image, fadeIn = true),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .clickable {
                    navController.navigate("detail")
                }
                .height(200.dp)
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row() {

            Text(
                text = searchModel.price,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp,
            )

            Spacer(modifier = Modifier.weight(1f))

        }

        Text(
            text = searchModel.name,
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
            lineHeight = 24.sp
        )

    }
}

val Textdownload = listOf<SearchModel>(
    SearchModel(
        "https://budujemydom.pl/i/2019/07/22/212180-1671-1100x0-sc1x50188_wiertarki-rodzaje5-budujemydompl.jpg",
        "test/test",
        "test_____________________--------test",
    ),

    SearchModel(
        "https://budujemydom.pl/i/2019/07/22/212180-1671-1100x0-sc1x50188_wiertarki-rodzaje5-budujemydompl.jpg",
        "test/test",
        "test_____________________--------test",
    ),

    SearchModel(
        "https://budujemydom.pl/i/2019/07/22/212180-1671-1100x0-sc1x50188_wiertarki-rodzaje5-budujemydompl.jpg",
        "test/test",
        "test_____________________--------test",
    ),

    SearchModel(
        "https://budujemydom.pl/i/2019/07/22/212180-1671-1100x0-sc1x50188_wiertarki-rodzaje5-budujemydompl.jpg",
        "test/test",
        "test_____________________--------test",
    ),


    SearchModel(
        "https://budujemydom.pl/i/2019/07/22/212180-1671-1100x0-sc1x50188_wiertarki-rodzaje5-budujemydompl.jpg",
        "test/test",
        "test_____________________--------test",
    ),

    SearchModel(
        "https://budujemydom.pl/i/2019/07/22/212180-1671-1100x0-sc1x50188_wiertarki-rodzaje5-budujemydompl.jpg",
        "test/test",
        "test_____________________--------test",
    ),
)

@Composable
fun RawItem(navController: NavController, searchModel: SearchModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .padding(4.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(color = DarkGray)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Image(
                painter = rememberCoilPainter(request = searchModel.image, fadeIn = true),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .clickable {
                    }
                    .height(245.dp)
                    .fillMaxWidth()
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(2.dp)
            ) {

                Text(
                    text = searchModel.price,
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp,
                )



                Text(
                    text = searchModel.name,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    lineHeight = 24.sp
                )
            }
        }
    }
}





