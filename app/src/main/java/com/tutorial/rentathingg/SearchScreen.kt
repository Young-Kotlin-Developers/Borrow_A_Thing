package com.tutorial.rentathingg

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.toSize
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.google.accompanist.insets.navigationBarsPadding


@Composable
fun SearchScreen(navController: NavController) {


    var AdTitle by remember {
        mutableStateOf("")
    }
    val isFormValid by derivedStateOf {
        AdTitle.isNotBlank()
    }

    Scaffold(backgroundColor = MaterialTheme.colors.primary) {
        Column(
            Modifier
                .navigationBarsPadding()
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top

        ) {
            Card(
                Modifier
                    .verticalScroll(rememberScrollState())
                    .weight(1f),
            ) {
                Column(
                    Modifier
                        .fillMaxSize()
                        .padding(top = 55.dp,start = 15.dp, end = 15.dp, bottom = 15.dp)
                ) {
                    Button(
                        shape = RoundedCornerShape(90.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                        onClick = {
//                            navController.navigate("home")//podpiać do home
                        }
                    ) {
                        Icon(
                            Icons.Filled.Close,
                            contentDescription = "Close",
                            modifier = Modifier.size(30.dp)
                        )
                    }
                    Column(
                        Modifier
                            .fillMaxSize()
                            .padding(top = 50.dp),
                    ) {
                        Text(
                            text = "You should put a ad",
                            fontWeight = FontWeight.Bold,
                            fontSize = 32.sp
                        )
                        Column(
                            Modifier
                                .fillMaxSize()
                                .padding(top = 50.dp),
                        ) {
                            Text(
                                text = "And the more detail, the better!",
                                fontWeight = FontWeight.Light,
                                fontSize = 20.sp
                            )
                            Card(
                                Modifier
                                    .padding(top = 10.dp)
                                    .fillMaxSize(),
                                elevation = 5.dp,
                                shape = RoundedCornerShape(32.dp)
                            ) {
                                Image(
                                    painterResource(id = R.drawable.the_camera),
                                    contentDescription = "The camera",
                                    modifier = Modifier
                                        .size(180.dp)


                                )
                            }
                            Column(
                                Modifier
                                    .fillMaxSize()
                                    .padding(top = 50.dp),
                            ) {
                                Text(
                                    text = "Ad title",
                                    fontWeight = FontWeight.Light,
                                    fontSize = 17.sp
                                )
                                OutlinedTextField(
                                    modifier = Modifier.fillMaxWidth(),
                                    value = AdTitle,
                                    onValueChange = { AdTitle = it },
                                    singleLine = true,
                                    trailingIcon = {
                                        if (AdTitle.isNotBlank())
                                            IconButton(onClick = { AdTitle = "" }) {
                                                Icon(
                                                    imageVector = Icons.Filled.Clear,
                                                    contentDescription = ""
                                                )
                                            }
                                    }
                                )
                                Spacer(modifier = Modifier.weight(1f))
                                Text(
                                    text = "Select categories",
                                    fontWeight = FontWeight.Light,
                                    fontSize = 17.sp
                                )
                                Column {
                                    dropDownMenu()
                                }
                                Text(
                                    text = "Details",
                                    fontWeight = FontWeight.Light,
                                    fontSize = 17.sp
                                )
                                Column(
                                    Modifier
                                        .fillMaxWidth()
                                        .height(160.dp)
                                ) {
                                    Description()
                                }
                                Text(
                                    text = "Description",
                                    fontWeight = FontWeight.Light,
                                    fontSize = 17.sp
                                )
                                Column(
                                    Modifier
                                        .fillMaxWidth()
                                        .height(160.dp)
                                ) {
                                    Description()
                                }
                                Text(
                                    text = "Phone number",
                                    fontWeight = FontWeight.Light,
                                    fontSize = 17.sp
                                )
                                Column(
                                    Modifier
                                        .fillMaxWidth()
//                                      .background(Color.Red)
                                ) {
                                    Phone_number()
                                }
                                Column(
                                    Modifier
                                        .fillMaxSize(),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center,
//                                        .background(Color.Red)
                                ) {
                                    Swich1()
                                }
                                Spacer(modifier = Modifier.height(16.dp))
                                Button(
                                    onClick = {},
                                    colors = ButtonDefaults.buttonColors(
                                        backgroundColor = Color(0xffEE4367),
                                        contentColor = Color(0xFFFFF5EE),
                                    ),
                                    modifier = Modifier.fillMaxWidth(),
                                    shape = RoundedCornerShape(16.dp)
                                ) {
                                    Text(text = "Dodaj ogłoszenie")
                                }

                            }
/*                            Column(
                                Modifier
                                    .fillMaxWidth()
                                    .height(55.dp)
                            ){}

 */


                        }
                    }
                }
            }
        }//koniec background kolumny
    }
}

@Composable
fun Swich1() {
    val checkedState = remember { mutableStateOf(true) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
        .padding(top = 15.dp,end = 15.dp),
    ) {
        Text(

            text = "Wyrażam zgodę na przetwarzanie moich \n" +
                    "danych osobowych w postaci podanego \n" +
                    "przeze mnie numeru telefonu przez \n" +
                    "NASZA FIRMA w celu przesyłania informacji" +
                    " handlowych oraz prowadzenia działań " +
                    "marketingowych przy użyciu telekomunikacyjnych urządzeń końcowych \n" +
                    " oraz automatycznych systemów \n" +
                    "wywołujących w rozumieniu ustawy Prawo \n" +
                    " telekomunikacyjne.",
        )
        Row(
            Modifier
                .fillMaxSize()
                .padding(top = 65.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Switch(
                checked = checkedState.value,
                onCheckedChange = { checkedState.value = it },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color(0xffEE4367),
                    uncheckedThumbColor =Color(0xff6162F5)
                )
            )
        }
    }
}

@Composable
fun Phone_number() {

    var phone_number by remember {
        mutableStateOf("")
    }
    val maxChar = 9

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = phone_number,
        onValueChange = {
            if (it.length < maxChar) {
                phone_number = it
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        singleLine = true,
        trailingIcon = {
            if (phone_number.isNotBlank())
                IconButton(onClick = { phone_number = "" }) {
                    Icon(
                        imageVector = Icons.Filled.Clear,
                        contentDescription = ""
                    )
                }
        }
    )

}

@Composable
fun Description() {
    var description by remember {
        mutableStateOf("")
    }

    val maxChar = 5000

    OutlinedTextField(
        modifier = Modifier
            .fillMaxSize(),
        value = description,
        onValueChange = {
            if (it.length < maxChar) {
                description = it
            }
        },
        maxLines = 7,
        trailingIcon = {
            if (description.isNotBlank())
                IconButton(onClick = { description = "" }) {
                    Icon(
                        imageVector = Icons.Filled.Clear,
                        contentDescription = ""
                    )
                }
        }
    )

}

@Composable
fun dropDownMenu() {

    var expanded by remember { mutableStateOf(false) }
    val suggestions = listOf("Item1", "Item2", "Item3")
    var selectedText by remember { mutableStateOf("") }

    var textfieldSize by remember { mutableStateOf(Size.Zero) }

    val icon = if (expanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown


    Column(Modifier.padding(0.dp)) {
        OutlinedTextField(
            value = selectedText,
            onValueChange = { selectedText = it },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    textfieldSize = coordinates.size.toSize()
                },
            trailingIcon = {
                Icon(icon, "contentDescription",
                    Modifier.clickable { expanded = !expanded })
            }
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current) { textfieldSize.width.toDp() })
        ) {
            suggestions.forEach { label ->
                DropdownMenuItem(onClick = {
                    selectedText = label
                    expanded = false
                }) {
                    Text(text = label)
                }
            }
        }
    }


}