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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.evelin.R
import com.example.evelin.ViewModelFactory
import com.example.evelin.ui.theme.Green
import com.example.evelin.ui.theme.LightGreen

@Composable
fun EventDetailsScreen(
    navController: NavController,
    eventId: String?,
    viewModel: EventDetailViewModel = viewModel(factory = ViewModelFactory.getInstance(LocalContext.current))
) {
    LaunchedEffect(eventId) {
        eventId?.let {
            viewModel.getEvent(it)
        }
    }

    val event by viewModel.events.collectAsState()
    val scrollState = rememberScrollState()

    if (event == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        val eventData = event!!
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            HeaderImage(eventData.posterUrl)

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = eventData.title ?: "Event Title",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    lineHeight = 28.sp
                )

                Spacer(modifier = Modifier.height(16.dp))

                EventInfoRow(
                    icon = painterResource(id = R.drawable.ic_calendar),
                    title = eventData.eventDate ?: "Event Date",
                    subtitle = "Saturday, 8:00AM - 10:30AM"
                )

                Spacer(modifier = Modifier.height(8.dp))

                EventInfoRow(
                    icon = painterResource(id = R.drawable.ic_location),
                    title = eventData.location ?: "Event Location",
                    subtitle = "Limau Manis, Kec. Pauh, Kota Padang, Sumatera Barat 25175"
                )

                Spacer(modifier = Modifier.height(8.dp))

                EventInfoRow(
                    icon = painterResource(id = R.drawable.ic_organizer),
                    title = eventData.university ?: "Organizer",
                    subtitle = "+6281286823201"
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "About Event",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = eventData.description ?: "Event Description",
                    fontSize = 14.sp,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(32.dp))

                Text(
                    text = if (eventData.isRegistered == 1) "You are registered for this event" else "You are not registered for this event",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (eventData.isRegistered == 1) Color.Green else Color.Red
                )

                Spacer(modifier = Modifier.height(16.dp))

                RegisterButton(navController, eventId, eventData.isRegistered == 1)
                Spacer(modifier = Modifier.height(16.dp))
                CancelButton(navController = navController)
            }
        }
    }
}

@Composable
fun HeaderImage(posterUrl: String?) {
    val image: Painter = if (posterUrl != null) {
        rememberAsyncImagePainter(posterUrl)
    } else {
        painterResource(id = R.drawable.foto_seminar)
    }
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
fun RegisterButton(navController: NavController, eventId: String?, isRegistered: Boolean) {
    Button(
        onClick = {
            eventId?.let {
                navController.navigate("registerEvent/$it")
            }
        },
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF26A541)),
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        enabled = !isRegistered
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
        onClick = { navController.popBackStack() },
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