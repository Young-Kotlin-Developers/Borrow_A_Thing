package com.tutorial.rentathingg

import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavController
import com.google.accompanist.insets.navigationBarsPadding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


@Composable
fun RegisterPage(navController: NavController) {
    val auth = Firebase.auth
    var usernameValue by remember {
        mutableStateOf("")
    }
    var emailValue by remember {
        mutableStateOf("")
    }
    var passwordValue by remember {
        mutableStateOf("")
    }
    var confirmPasswordValue by remember {
        mutableStateOf("")
    }
    var isPasswordVisible by remember {
        mutableStateOf(false)
    }
    val isFormValid by derivedStateOf {
        usernameValue.isNotBlank() && emailValue.isNotBlank() && passwordValue.length >= 7 && confirmPasswordValue.length >= 7


    }

    Scaffold(backgroundColor = MaterialTheme.colors.primary) {
        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Image(
                painterResource(id = R.drawable.sign_off),
                contentDescription = "App Logo",
                modifier = Modifier
                    .weight(2.5f)
                    .size(190.dp)

            )
            Card(
                Modifier
                    .weight(5f)
                    .navigationBarsPadding()
                    .padding(8.dp),
                shape = RoundedCornerShape(32.dp)
            ) {
                Column(
                    Modifier
                        .fillMaxSize()
                        .padding(19.dp)
                ) {
                    Text(text = "Register", fontWeight = FontWeight.Bold, fontSize = 32.sp)
                    Text(
                        text = "It's quick and easy",
                        fontWeight = FontWeight.Light,
                        fontSize = 16.sp
                    )
                    Column(
                        Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Spacer(modifier = Modifier.weight(1f))
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = usernameValue,
                            onValueChange = { usernameValue = it },
                            label = { Text(text = "Username") },
                            singleLine = true,
                            trailingIcon = {
                                if (usernameValue.isNotBlank())
                                    IconButton(onClick = { usernameValue = "" }) {
                                        Icon(
                                            imageVector = Icons.Filled.Clear,
                                            contentDescription = ""
                                        )
                                    }
                            }
                        )

                        Spacer(modifier = Modifier.height(8.dp))
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = emailValue,
                            onValueChange = { emailValue = it },
                            label = { Text(text = "Email Address") },
                            singleLine = true,
                            trailingIcon = {
                                if (emailValue.isNotBlank())
                                    IconButton(onClick = { emailValue = "" }) {
                                        Icon(
                                            imageVector = Icons.Filled.Clear,
                                            contentDescription = ""
                                        )
                                    }
                            }
                        )

                        Spacer(modifier = Modifier.height(8.dp))
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = passwordValue,
                            onValueChange = { passwordValue = it },
                            label = { Text(text = "Password") },
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Password,
                                imeAction = ImeAction.Done
                            ),
                            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                            trailingIcon = {
                                IconButton(onClick = {
                                    isPasswordVisible = !isPasswordVisible
                                }) {
                                    Icon(
                                        imageVector = if (isPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                        contentDescription = "Password Toggle"
                                    )
                                }
                            }
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = confirmPasswordValue,
                            onValueChange = { confirmPasswordValue = it },
                            label = { Text(text = "Confirm Password") },
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Password,
                                imeAction = ImeAction.Done
                            ),
                            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                            trailingIcon = {
                                IconButton(onClick = {
                                    isPasswordVisible = !isPasswordVisible
                                }) {
                                    Icon(
                                        imageVector = if (isPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                        contentDescription = "Password Toggle"
                                    )
                                }
                            }
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Button(
                            enabled = isFormValid,
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(16.dp),
                            onClick = {
                                auth.createUserWithEmailAndPassword(
                                    emailValue.trim(),
                                    passwordValue.trim()
                                )
                                    .addOnCompleteListener { task ->
                                        if (task.isSuccessful) {
                                            Log.d("AUTH", "Success!")
                                            navController.navigate("Home")
                                        } else {
                                            Log.d("AUTH", "Failed ${task.exception}")
                                        }
                                    }
                            }
                        ) {
                            Text(text = "Sign up")
                        }
                        Spacer(modifier = Modifier.height(2.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            TextButton(onClick = {
                                navController.navigate("SigIn")
                            }) {
                                Text(text = "back")
                            }
                        }
                    }
                }
            }
        }
    }
}








