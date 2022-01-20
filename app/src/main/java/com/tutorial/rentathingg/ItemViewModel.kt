package com.tutorial.rentathingg

import android.content.ContentValues
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.ktx.toObjects

class ItemViewModel:ViewModel() {
    private  val _books= mutableStateOf<List<ItemResult>>(listOf())
    val books: State<List<ItemResult>>
        get()=_books
    val query= FirebaseFirestore.getInstance().collection("items")

    init {
//        query.get().addOnSuccessListener { result ->
//            for (document in result) {
//                val item = document.toObject<ItemResult>()
//                listawynikow.add(item)
//                Log.d("listawynikow",listawynikow.toString())
//            }
//        }
//            .addOnFailureListener { exception ->
//                Log.d(ContentValues.TAG, "Error getting documents: ", exception)
//            }
        query.addSnapshotListener{ snapshot, _ ->
            snapshot?.let{querySnapshot ->
                _books.value = querySnapshot.toObjects()
            }
        }
    }
}