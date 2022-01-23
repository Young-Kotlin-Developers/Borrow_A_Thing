import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.google.firebase.firestore.FirebaseFirestore
import com.tutorial.rentathingg.ItemResult
import com.tutorial.rentathingg.ItemViewModel
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tutorial.rentathingg.R

fun usuwaniezbazydanych(id: String) {
    val query = FirebaseFirestore.getInstance().collection("items")
    if (id != null) {
        query.document(id).delete()
    }


}


fun pobraniekonkretnychbazydanych(tytuldousuniecia: String?) {
    var id = ""
    val query = FirebaseFirestore.getInstance().collection("items")
    query.whereEqualTo("title", tytuldousuniecia).get().addOnSuccessListener { documents ->
        for (document in documents) {
            id = document.id
            if (id != "") {
                usuwaniezbazydanych(id)
                break
            }

        }
    }
}

// text = "Gratuluje zamówienia ${myItem[number].title}"
@Composable
fun MoneyScreen(navController: NavController, itemViewModel: ItemViewModel, number: Int?) {
    val myItem = listOf(itemViewModel.books.value[number!!])
    Log.d("itemprzeslany", myItem.toString())

    Scaffold(backgroundColor = Color.White) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center

        ) {
            Text(
                fontSize = 20.sp,
                text = "Gratuluje zamówienia ${myItem[number].title}",
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(4.dp))
            Image(
                painterResource(id = R.drawable.thank_you),
                contentDescription = "thank_you",
                modifier = Modifier
                    .padding(8.dp)
                    .size(300.dp)
            )
            Button(
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth(),

                onClick = {
                    var tytuldousuniecia = ""
                    for (document in myItem) {
                        tytuldousuniecia = document.title
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
}
