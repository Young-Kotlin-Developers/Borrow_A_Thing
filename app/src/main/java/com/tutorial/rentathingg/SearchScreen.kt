package com.tutorial.rentathingg

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.R
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
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.toSize
import androidx.navigation.NavController
import com.google.accompanist.insets.navigationBarsPadding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageMetadata
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream

class TextFieldState() {
    var text: String by mutableStateOf("")
}

fun compressImage(context: ComponentActivity, uri: Uri): Uri? {
    val bitmap = if (Build.VERSION.SDK_INT < 28) {
        MediaStore.Images.Media.getBitmap(context.contentResolver, uri)
    } else {
        val source = ImageDecoder.createSource(context.contentResolver, uri)
        ImageDecoder.decodeBitmap(source)
    }
    val bytes = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.JPEG, 60, bytes)
    val path: String = MediaStore.Images.Media.insertImage(
        context.contentResolver, bitmap, "Title", null
    )
    return Uri.parse(path)
}

private suspend fun uploadPhoto(
    uri: Uri,
    name: String,
    mimeType: String?,
    callback: (url: String) -> Unit
) {
    val storage = FirebaseStorage.getInstance()
    val storageRef = storage.reference
    val fileRef = storageRef.child("images/$name")

    val metadata = mimeType?.let {
        StorageMetadata.Builder()
            .setContentType(mimeType)
            .build()
    }

    if (metadata != null) {
        fileRef.putFile(uri, metadata)
//        fileRef.putFile(uri, metadata).await()
    } else {
        fileRef.putFile(uri)
//        fileRef.putFile(uri).await()
    }

    callback(fileRef.downloadUrl.toString())
//    callback(fileRef.downloadUrl.await().toString())
}

fun writeNewItem(
    title: String,
    category: String,
    detail: String,
    description: String,
    phoneNum: String,
    value: String,
    author: String
) {
    lateinit var database: DatabaseReference
    database = Firebase.database.reference
    val item = Item(title, category, detail, description, phoneNum, value, author)

    val id = FirebaseDatabase.getInstance().getReference("items").push().key
    if (id != null) {
        database.child("items").child(id).setValue(item)
    }
}

@Composable
fun SearchScreen(navController: NavController) {

    var title by remember { mutableStateOf("") }
    var details = remember { TextFieldState() }
    var description = remember { TextFieldState() }
    var phoneNumber = remember { TextFieldState() }
    var value = remember { TextFieldState() }

    val isFormValid by derivedStateOf {
        title.isNotBlank() && details.text.isNotBlank() && description.text.isNotBlank() && phoneNumber.text.length > 8 && value.text.length > 1
    }

    val categoryItems = listOf("Tools", "Electronics", "Cars", "Books")
    var categoriesExpanded by remember { mutableStateOf(false) }
    var selectedIndex by remember { mutableStateOf(0) }

    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val context = LocalContext.current
    val bitmap = remember { mutableStateOf<Bitmap?>(null) }

    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
            imageUri = uri
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
                        .padding(top = 55.dp, start = 15.dp, end = 15.dp, bottom = 15.dp)
                ) {
                    Button(
                        shape = RoundedCornerShape(90.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                        onClick = {
                            //TODO: navController.navigate("home") - PODPIAC DO HOME
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
                            imageUri?.let {
                                if (Build.VERSION.SDK_INT < 28) {
                                    bitmap.value = MediaStore.Images.Media.getBitmap(
                                        context.contentResolver,
                                        it
                                    )
                                } else {
                                    val source =
                                        ImageDecoder.createSource(context.contentResolver, it)
                                    bitmap.value = ImageDecoder.decodeBitmap(source)
                                }

                                bitmap.value?.let { btm ->
                                    Image(
                                        bitmap = btm.asImageBitmap(),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .size(300.dp)
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.height(12.dp))
                            Button(onClick = { launcher.launch("image/*") }) {
                                Text(text = "Pick Image")
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
                                    value = title,
                                    onValueChange = { title = it },
                                    singleLine = true,
                                    trailingIcon = {
                                        if (title.isNotBlank())
                                            IconButton(onClick = { title = "" }) {
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
//                                    dropDownMenu()
//                                    DropDownMenu(value = category) { category = it }
                                    ComposeMenu(
                                        menuItems = categoryItems,
                                        menuExpandedState = categoriesExpanded,
                                        seletedIndex = selectedIndex,
                                        updateMenuExpandStatus = {
                                            categoriesExpanded = true
                                        },
                                        onDismissMenuView = {
                                            categoriesExpanded = false
                                        },
                                        onMenuItemclick = { index ->
                                            selectedIndex = index
                                            categoriesExpanded = false
                                        }
                                    )
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
                                    Description(details)
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
                                    Description(description)
                                }
                                Text(
                                    text = "Phone number",
                                    fontWeight = FontWeight.Light,
                                    fontSize = 17.sp
                                )
                                Column(
                                    Modifier
                                        .fillMaxWidth()
                                ) {
                                    Phone_number(phoneNumber)
                                }
                                Text(
                                    text = "value",
                                    fontWeight = FontWeight.Light,
                                    fontSize = 17.sp
                                )
                                Column(
                                    Modifier
                                        .fillMaxWidth()
                                ) {
                                    Value(value)
                                }
                                Column(
                                    Modifier
                                        .fillMaxSize(),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center,
                                ) {
                                    Swich1()
                                }
                                Spacer(modifier = Modifier.height(16.dp))
                                Button(
                                    enabled = isFormValid,
                                    colors = ButtonDefaults.buttonColors(
                                        backgroundColor = Color(0xffEE4367),
                                        contentColor = Color(0xFFFFF5EE),
                                    ),
                                    modifier = Modifier.fillMaxWidth(),
                                    shape = RoundedCornerShape(16.dp),
                                    onClick = {
                                        val user = Firebase.auth.currentUser
                                        if (user != null) {
                                            user?.let {
                                                // Name, email address, and profile photo Url
                                                val username = user.displayName
//                                            val email = user.email
//                                            val photoUrl = user.photoUrl

                                                // Check if user's email is verified
//                                            val emailVerified = user.isEmailVerified

                                                // The user's ID, unique to the Firebase project. Do NOT use this value to
                                                // authenticate with your backend server, if you have one. Use
                                                // FirebaseUser.getToken() instead.
//                                            val uid = user.uid

                                                if (username != null) {
                                                    val imageName = "${System.currentTimeMillis()}.jpg"
                                                    GlobalScope.launch(Dispatchers.IO) {
                                                        val compressedImage = compressImage(
                                                            context as ComponentActivity,
                                                            imageUri!!
                                                        )
                                                        uploadPhoto(
                                                            compressedImage!!,
                                                            imageName,
                                                            "image/jpg"
                                                        ) {
                                                            GlobalScope.launch(Dispatchers.Main) {
                                                                Log.d(
                                                                    "UPLOAD",
                                                                    "FILE UPDATED !!!!!!!!!!!!!!!!!"
                                                                )
                                                            }
                                                        }
                                                    }

                                                    FirebaseStorage.getInstance().reference.child("/images/${imageName}").downloadUrl.addOnSuccessListener {
                                                        Log.d("IMAGE", it.toString())
                                                    }.addOnFailureListener {
                                                        Log.d("IMAGE", "FAILED TO GET IMAGE PATH!!!!!!!!!!!!!!!")
                                                    }
                                                    Log.d("IMAGESS", "images/${imageName}")

                                                    writeNewItem(
                                                        title,
                                                        categoryItems[selectedIndex],
                                                        details.text,
                                                        description.text,
                                                        phoneNumber.text,
                                                        value.text,
                                                        username,
                                                    )
                                                }
                                            }
                                        } else {
                                            Log.d("USER", "NO USER FOUND !!!!!!!!!!!!")
                                        }
                                    }
                                ) {
                                    Text(text = "Dodaj ogłoszenie")
                                }

                            }
                        }
                    }
                }
            }
        }//koniec background kolumny
    }
}

@Composable
fun ComposeMenu(
    menuItems: List<String>,
    menuExpandedState: Boolean,
    seletedIndex: Int,
    updateMenuExpandStatus: () -> Unit,
    onDismissMenuView: () -> Unit,
    onMenuItemclick: (Int) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.TopStart)
            .padding(top = 10.dp)
            .border(0.5.dp, MaterialTheme.colors.onSurface.copy(alpha = 0.5f))
            .clickable(
                onClick = {
                    updateMenuExpandStatus()
                },
            ),

        ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = menuItems[seletedIndex],
                modifier = Modifier
                    .fillMaxWidth(),
//                trailingIcon = {
//                    Icon(Icons.Filled.KeyboardArrowDown, "categories")
//                }
            )

            Icon(
                Icons.Filled.KeyboardArrowDown,
                contentDescription = null,
                modifier = Modifier
                    .size(20.dp, 20.dp)
                    .padding(start = 30.dp, end = 0.dp)
            )

            DropdownMenu(
                expanded = menuExpandedState,
                onDismissRequest = { onDismissMenuView() },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.surface)
            ) {
                menuItems.forEachIndexed { index, title ->
                    DropdownMenuItem(
                        onClick = {
                            if (index != 0) {
                                onMenuItemclick(index)
                            }
                        }) {
                        Text(text = title)
                    }
                }
            }
        }
    }
}

/*
@Composable
fun DropDownMenu(selectedText: String, onInputChanged: (String) -> Unit) {

    var expanded by remember { mutableStateOf(false) }
    val suggestions = listOf("Electronics", "Cars", "Tools")
    var selectedText by remember { mutableStateOf("") }

    var textfieldSize by remember { mutableStateOf(Size.Zero) }

    val icon = if (expanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    Column(Modifier.padding(0.dp)) {
        OutlinedTextField(
            value = selectedText,
//            onValueChange = { selectedText = it },
            onValueChange = onInputChanged,
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

 */
@Composable
fun Description(description: TextFieldState = remember { TextFieldState() }) {
    val maxChar = 5000

    OutlinedTextField(
        modifier = Modifier
            .fillMaxSize(),
        value = description.text,
        onValueChange = {
            if (it.length < maxChar) {
                description.text = it
            }
        },
        maxLines = 7,
        trailingIcon = {
            if (description.text.isNotBlank())
                IconButton(onClick = { description.text = "" }) {
                    Icon(
                        imageVector = Icons.Filled.Clear,
                        contentDescription = ""
                    )
                }
        }
    )
}

@Composable
fun Value(phoneNumber: TextFieldState = remember { TextFieldState() }) {
    val maxChar = 5

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = phoneNumber.text,
        onValueChange = {
            if (it.length < maxChar) {
                phoneNumber.text = it
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        singleLine = true,
        trailingIcon = {
            if (phoneNumber.text.isNotBlank())
                IconButton(onClick = { phoneNumber.text = "" }) {
                    Icon(
                        imageVector = Icons.Filled.Clear,
                        contentDescription = ""
                    )
                }
        }
    )
}

@Composable
fun Phone_number(phoneNumber: TextFieldState = remember { TextFieldState() }) {
    val maxChar = 10

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = phoneNumber.text,
        onValueChange = {
            if (it.length < maxChar) {
                phoneNumber.text = it
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        singleLine = true,
        trailingIcon = {
            if (phoneNumber.text.isNotBlank())
                IconButton(onClick = { phoneNumber.text = "" }) {
                    Icon(
                        imageVector = Icons.Filled.Clear,
                        contentDescription = ""
                    )
                }
        }
    )
}

@Composable
fun Swich1() {
    val checkedState = remember { mutableStateOf(false) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 15.dp),
    ) {
        Column(
            Modifier
                .height(200.dp)
                .width(320.dp)
        ) {
            Text(
                text = "Wyrażam zgodę na przetwarzanie moich " +
                        "danych osobowych w postaci podanego " +
                        "przeze mnie numeru telefonu przez " +
                        "NASZA FIRMA w celu przesyłania  " +
                        "informacji handlowych oraz prowadzenia działań " +
                        "marketingowych przy użyciu telekomunikacyjnych urządzeń końcowych " +
                        " oraz automatycznych systemów " +
                        "wywołujących w rozumieniu ustawy Prawo " +
                        " telekomunikacyjne.",
            )
            //TU POWINIEN BYĆ ZAMKNIECIE COLUMNY
        }
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
                    uncheckedThumbColor = Color(0xff6162F5)
                )
            )
        }
    }
//    }
}

