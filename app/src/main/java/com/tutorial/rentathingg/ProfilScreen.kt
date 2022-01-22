package com.tutorial.rentathingg

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.accompanist.coil.rememberCoilPainter
import com.google.accompanist.insets.navigationBarsPadding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


@Composable
fun ProfilScreen(navController: NavController, viewmodel: ItemViewModel) {

    Scaffold(backgroundColor = Color.White) {
        LazyColumn(
        ) {
            item {
                Up(navController)
                Spacer(modifier = Modifier.height(30.dp))
                Down(navController, viewmodel)
                logout(navController)
            }
        }
    }
}


@Composable
fun Up(navController: NavController) {
    val user = Firebase.auth.currentUser
    Column(
        modifier = Modifier
            .padding(top = 50.dp, start = 20.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
    ) {
        Image(
            painterResource(id = R.drawable.profil),
            contentDescription = "App Logo",
            modifier = Modifier
                .size(120.dp),

            )
        Column(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            if (user != null) {
                user.displayName?.let {
                    Text(
                        text = "Hello ${it}!",
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp,
                        color = Color.Black
                    )
                }
            }
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = "Number of advertisements: ",
                fontWeight = FontWeight.Normal,
                fontSize = 15.sp,
                color = Color.Black

            )
        }
    }
}


@Composable
fun Down(navController: NavController, viewModel: ItemViewModel) {
    Column(
        Modifier
            .padding(8.dp)
            .fillMaxSize(),

        verticalArrangement = Arrangement.Top,
    ) {
        Button(
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth(),

            onClick = {
                navController.navigate("Offer")
            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xffEE4367),
                contentColor = Color(0xFFFFF5EE),
            ),
            shape = RoundedCornerShape(10.dp),
        ) {
            Text(text = "Add advertisement", color = Color.White)
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Your adverts",
            fontWeight = FontWeight.SemiBold,
            fontSize = 15.sp,
            color = Color.Black
        )
        myItems(searchModel = viewModel.books.value[0], navController = navController, 0)
        myItems(searchModel = viewModel.books.value[1], navController = navController, 1)
        Spacer(modifier = Modifier.height(10.dp))
        TextButton(
            onClick = {},
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xFFFFFFFF),
                contentColor = Color(0xFF000000),
            ),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(text = "Active")
            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = null,
                modifier = Modifier.padding(start = 280.dp)
            )
        }
        Divider(
            color = Color(0xFFECECEE),
            modifier = Modifier.padding(5.dp)
        )
    }
}

@Composable
fun myItems(searchModel: ItemResult, navController: NavController, position: Int) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
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

@Composable
fun logout(navController: NavController) {
    val context = LocalContext.current

    Column(
        Modifier
            .padding(8.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TextButton(
            onClick = {
                Toast.makeText(
                    context,
                    "You have been logged out of the application",
                    Toast.LENGTH_SHORT
                ).show()
                FirebaseAuth.getInstance().signOut();
                //TODO: !!!!!!!!!!!!!!!!
                //TODO: SPECJALNY BLAD ZEBY WYWALILO APKE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                //TODO: !!!!!!!!!!!!!!!!
                navController.navigate("Siii") // tutaj zle - specjalny blad
//                navController.navigate("SigIn") // powinno byc tak
            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xFFFFFFFF),
                contentColor = Color(0xFF000000),
            ),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(text = "Log out")
        }
        Divider(
            color = Color(0xFF000000),
            modifier = Modifier
                .padding(start = 150.dp, end = 150.dp)
        )
    }
}
