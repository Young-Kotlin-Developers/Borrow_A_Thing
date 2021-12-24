package com.tutorial.rentathingg

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.accompanist.coil.rememberCoilPainter
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding

@Composable
fun DetailScreen(navController: NavController) {

    Scaffold(backgroundColor = Color.White) {

        LazyColumn() {
            Modifier

            item {
                Photoadapter(navController)
                Infoproduct()
            }

            itemsIndexed(tripDays) { position, data ->
                TextContent(data)
            }
            item {
                BottomNav()
            }


        }
    }
}

@Composable
fun Photoadapter(navController: NavController) {

    val detailHeaderImageUrl =
        "https://e-irwin.pl/pol_pl_Mlotowiertarka-SDS-Plus-28mm-z-QCC-900W-DeWalt-140659_1.jpg"

    Box() {

        Image(
            painter = rememberCoilPainter(request = detailHeaderImageUrl),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        )


        Box(
            modifier = Modifier
                .statusBarsPadding()
                .fillMaxWidth()
        ) {

            TopButton(
                imageVector = Icons.Default.ArrowBack,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(16.dp)
            ) {
                navController.popBackStack()
            }

        }


    }

}


@Composable
fun Infoproduct() {
    Divider(
        color = Color(0xFFECECEE),
        modifier = Modifier.padding(0.dp)
    )
    Column(
        Modifier
            .fillMaxSize()
            .padding(15.dp)
    ) {
        Text(
            text = "Dodano dzisiaj o 19:30",
            fontWeight = FontWeight.Light,
            fontSize = 10.sp
        )
        Text(
            text = "Wiertara Andrzeja",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
        Text(
            text = "20 zł",
            fontWeight = FontWeight.Normal,
            fontSize = 18.sp
        )
        Divider(
            color = Color(0xFFECECEE),
            modifier = Modifier.padding(8.dp)
        )
    }
}

data class TextData(val title: String, val detail: String)

var tripDays = listOf(
    TextData(
        title = "Detale: ",
        detail ="Moc pobierana 701 W \n" +
                "Moc użyteczna 302 W \n" +
                "Maks. moment obrotowy 8.6 Nm \n" +
                "Prędkość bez obciążenia 0-2800 obr/min\n" +
                "Częstość udarów 47600 ud/min \n" +
                "Maks. średnica wiercenia [Drewno] 25 mm \n" +
                "Maks. śr. wiercenia w stali 13 mm \n" +
                "Maks. śr. wiercenia w betonie 16 mm \n" +
                "Gwint wrzeciona 1/2 x 20 U.N.F \n" +
                "Masa 1,82 kg \n" +
                "Długość 255 mm\n"
    ),
    TextData(
        title = "Opis: ",
        detail = "-Niewielka waga umożliwia łatwe sterowanie i pracę bez zmęczenia Zwarta budowa gwarantuje wygodne użytkowanie również w miejscach o ograniczonym dostępie.\n" +
                " -Doskonały stosunek mocy do gabarytów \n" +
                "- mocna, wytrzymała wiertarka o niewielkich rozmiarach. \n" +
                "-Uszczelniony przed pyłem włącznik ze sterowaniem prędkością obrotową umożliwia płynne i precyzyjne wiercenie, szczególnie przy wstępnym nawiercaniu otworów. \n" +
                "-Gumowana rękojeść zwiększa pewność i wygodę pracy przy wierceniach z udarem i bez . \n" +
                "-Włącznik z blokadą stanu włączenia umożliwia pracę ciągłą przy powtarzalnych wierceniach.\n"
    ),
)


@Composable
fun TextContent(day: TextData) {

    Column(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
    ) {

        Text(
            text = day.title.uppercase(),
            fontSize = 14.sp,
            fontWeight = FontWeight.ExtraBold,
            letterSpacing = 0.75.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = day.detail,
            fontSize = 14.sp,
            fontWeight = FontWeight.Light,
            lineHeight = 18.sp
        )

    }

}

@Composable
fun TopButton(imageVector: ImageVector, modifier: Modifier, clickListener: () -> Unit) {


    Button(
        onClick = { clickListener() },
        border = BorderStroke(2.dp, Color(0xDDF6F9FF)),
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(0xDDF6F9FF),
            contentColor = Color(0xff6162F5)
        ),
        modifier = modifier.size(48.dp)

    ) {

        Icon(imageVector = imageVector, contentDescription = "")
    }
}

@Composable
fun BottomNav() {
    Column(
        Modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .padding(10.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xffEE4367),
                    contentColor = Color(0xFFFFF5EE),
                ),
                shape = RoundedCornerShape(16.dp),
            ) {
                Icon(
                    Icons.Filled.Chat,
                    contentDescription = "Email",
                    modifier = Modifier.size(ButtonDefaults.IconSize)
                )
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                Text(text = "Wiadomość")
            }
            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xffEE4367),
                    contentColor = Color(0xFFFFF5EE),

                    ),
                shape = RoundedCornerShape(16.dp),

                ) {
                Icon(
                    Icons.Filled.Phone,
                    contentDescription = "Phone",
                    modifier = Modifier.size(ButtonDefaults.IconSize)
                )
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                Text(text = "Zadzwoń", color = Color.White)
            }
        }
    }

}
