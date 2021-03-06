package com.tutorial.rentathingg

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.google.accompanist.insets.navigationBarsPadding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.tutorial.rentathingg.ui.theme.MainColor
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .navigationBarsPadding()
        ) {
            HeroSection()
            SearchSection(navController)

        }
    }
}

@Composable
fun HeroSection() {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
        )
        {
            BoxWithConstraints {
                if (maxWidth < 400.dp) {
                    Image(
                        painter = painterResource(R.drawable.sasiedzi),
                        contentDescription = "Hero picture",
                        contentScale = ContentScale.FillWidth,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(250.dp)
                    )
                } else {
                    Image(
                        painter = painterResource(R.drawable.sasiedzi),
                        contentDescription = "Hero picture",
                        contentScale = ContentScale.FillWidth,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(430.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Do you lack equipment?",
                fontSize = 30.sp,
                fontWeight = FontWeight.W500,
                textAlign = TextAlign.Center
            )
            Text(
                text = "The neighbor will lend you!",
                fontSize = 30.sp,
                fontWeight = FontWeight.W500,
                textAlign = TextAlign.Center
            )
        }
    }

}

@Composable
fun SearchSection(navController: NavController) {
    val scaffoldState = rememberScaffoldState()
//    var itemFieldState by remember {
//        mutableStateOf("")
//    }
//    var categoryFieldState by remember {
//        mutableStateOf("")
//    }
    val scope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        scaffoldState = scaffoldState
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
//            Spacer(modifier = Modifier.height(5.dp))
//            Text(text = "Czego ci potrzeba?", style = MaterialTheme.typography.h5)
//            Spacer(modifier = Modifier.height(8.dp))
//            OutlinedTextField(
//                value = itemFieldState,
//                label = { Text(text = "Szukaj po nazwie...") },
//                onValueChange = { itemFieldState = it },
//                singleLine = true,
//                modifier = Modifier.fillMaxWidth(),
//                trailingIcon = {
//                    if (itemFieldState.isNotBlank())
//                        IconButton(onClick = { itemFieldState = "" }) {
//                            Icon(imageVector = Icons.Filled.Clear, contentDescription = "")
//                        }
//                }
//            )
//            Spacer(modifier = Modifier.height(12.dp))
//            Text(text = "LUB")
//            Spacer(modifier = Modifier.height(12.dp))
//            OutlinedTextField(
//                value = categoryFieldState,
//                label = { Text(text = "Kategoria...") },
//                onValueChange = { categoryFieldState = it },
//                singleLine = true,
//                modifier = Modifier.fillMaxWidth(),
//                trailingIcon = {
//                    if (categoryFieldState.isNotBlank())
//                        IconButton(onClick = { categoryFieldState = "" }) {
//                            Icon(imageVector = Icons.Filled.Clear, contentDescription = "")
//                        }
//                }
//            )
//            Spacer(modifier = Modifier.height(20.dp))
            Button(
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MainColor,
                    contentColor = Color.White
                ),
                onClick = {
                    scope.launch {
                        navController.navigate("Result") {
                        }
//                    scaffoldState.snackbarHostState.showSnackbar("Item: $itemFieldState")
                    }
                }) {
                Text("Search items", style = MaterialTheme.typography.h5)
            }
        }
    }
}