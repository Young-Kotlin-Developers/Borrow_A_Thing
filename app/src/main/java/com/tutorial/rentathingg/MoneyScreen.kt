
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.google.firebase.firestore.FirebaseFirestore
import com.tutorial.rentathingg.ItemResult
import com.tutorial.rentathingg.ItemViewModel
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.ui.unit.dp

fun usuwaniezbazydanych(id:String){
    val query= FirebaseFirestore.getInstance().collection("items")
    if(id!=null){
        query.document(id).delete()
    }


}


fun pobraniekonkretnychbazydanych(tytuldousuniecia:String?) {
    var id = ""
    val query = FirebaseFirestore.getInstance().collection("items")
    query.whereEqualTo("title", tytuldousuniecia).get().addOnSuccessListener { documents ->
        for (document in documents) {
            id = document.id
            if(id!=""){
                usuwaniezbazydanych(id)
                break
            }

        }
    }
}

// text = "Gratuluje zamówienia ${myItem[number].title}"
@Composable
fun MoneyScreen(navController: NavController,itemViewModel:ItemViewModel,number:Int?) {
    val myItem = listOf(itemViewModel.books.value[number!!])
    Log.d("itemprzeslany",myItem.toString())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray),
        verticalArrangement = Arrangement.Top,
    ) {
        Text(
            text = "Gratuluje zamówienia ${myItem[number].title}",
            fontSize = MaterialTheme.typography.h3.fontSize,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Button(
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth(),

            onClick = {
                    var tytuldousuniecia=""
                    for(document in myItem){
                        tytuldousuniecia=document.title
                    }
                    navController.navigate("Home")
                    pobraniekonkretnychbazydanych(tytuldousuniecia)

                      },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xffEE4367),
                contentColor = Color(0xFFFFF5EE),
            ),
            shape = RoundedCornerShape(10.dp),
        ) {
            Text(text = "Powrót do wyszukiwania", color = Color.White)
        }
    }
}
