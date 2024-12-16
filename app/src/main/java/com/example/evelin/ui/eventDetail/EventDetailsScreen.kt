package com.example.evelin.ui.eventDetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.evelin.R
import com.example.evelin.ui.theme.Green
import com.example.evelin.ui.theme.LightGreen

@Composable
fun EventDetailsScreen(navController: NavController) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        // Header Image
        HeaderImage()

        // Event Content
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Title
            Text(
                text = "Transformasi Digital di Era Industri 4.0",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                lineHeight = 28.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Event Date
            EventInfoRow(
                icon = painterResource(id = R.drawable.ic_calendar),
                title = "19 Oktober, 2024",
                subtitle = "Saturday, 8:00AM - 10:30AM"
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Event Location
            EventInfoRow(
                icon = painterResource(id = R.drawable.ic_location),
                title = "Auditorium Universitas Andalas",
                subtitle = "Limau Manis, Kec. Pauh, Kota Padang, Sumatera Barat 25175"
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Organizer
            EventInfoRow(
                icon = painterResource(id = R.drawable.ic_organizer),
                title = "BEM KM FTI UNAND",
                subtitle = "+6281286823201"
            )

            Spacer(modifier = Modifier.height(16.dp))

            // About Event Section
            Text(
                text = "About Event",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Pelajari bagaimana teknologi mengubah dunia kerja, dapatkan wawasan dari para ahli, dan siapkan dirimu untuk bersaing di era digital. Jangan lewatkan kesempatan ini untuk mempersiapkan masa depanmu!",
                fontSize = 14.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Register Button
            RegisterButton(navController)
            Spacer(modifier = Modifier.height(16.dp))
            CancelButton(navController = navController) // Pass navController here

        }
    }
}

@Composable
fun HeaderImage() {
    val image: Painter = painterResource(id = R.drawable.foto_seminar) // Replace with your image resource
    Image(
        painter = image,
        contentDescription = "Event Header Image",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    )
}

@Composable
fun EventInfoRow(icon: Painter, title: String, subtitle: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = icon,
            contentDescription = null,
            modifier = Modifier
                .size(32.dp)
                .padding(end = 8.dp)
        )
        Column {
            Text(
                text = title,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Text(
                text = subtitle,
                fontSize = 12.sp,
                color = Color.Gray
            )
        }
    }
}

@Composable
fun RegisterButton(navController: NavController) {
    Button(
        onClick = { navController.navigate("registerEvent") },
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF26A541)),
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
    ) {
        Text(
            text = "Register Here",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun CancelButton(navController: NavController) {
    Button(
        onClick = { navController.popBackStack() }, // Navigate back on click
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(containerColor = LightGreen),
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
    ) {
        Text(
            text = "Cancel",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Green,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewEventDetailsScreen() {
    val navController = rememberNavController()
    EventDetailsScreen(navController = navController)
}