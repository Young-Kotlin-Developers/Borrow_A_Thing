package com.tutorial.rentathingg

import android.util.Log
import androidx.compose.foundation.Image
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.database.*


@Composable
fun ResultScreen(navController: NavController) {

    val value=1
    val listawynikow=arrayListOf<ItemResult>()
    val dbref= FirebaseDatabase.getInstance().getReference("items")
    if(value==1){
        dbref.orderByChild("author").equalTo("maciek99").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if(dataSnapshot.exists()){
                    for (postSnapshot in dataSnapshot.children) {
                        val item = postSnapshot.getValue(ItemResult::class.java)
                        listawynikow.add(item!!)
                        Log.d("result", item.toString())
                    }
                }}


            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
    else if(value==0){
        dbref.orderByChild("category").equalTo("maciek99").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if(dataSnapshot.exists()){
                    for (postSnapshot in dataSnapshot.children) {
                        var item = postSnapshot.getValue(ItemResult::class.java)
                        listawynikow.add(item!!)
                        Log.d("result", item.toString())
                    }
                }}


            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

}

