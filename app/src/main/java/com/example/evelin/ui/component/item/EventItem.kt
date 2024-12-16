package com.example.evelin.ui.component.item

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.evelin.R

@Composable
fun EventItem(navController: NavController, eventName: String, eventDescription: String, eventImage: Painter) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .size(200.dp)
            .clickable { navController.navigate("eventDetails") }, // Handle navigation on click
        shape = RoundedCornerShape(8.dp)
    ) {
        Column {
            Image(
                painter = eventImage,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = eventName,
                fontSize = 16.sp,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = eventDescription,
                fontSize = 12.sp,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EventItemPreview() {
    // Use a dummy NavController for preview
    val navController = rememberNavController()
    EventItem(
        navController = navController,
        eventName = "Sample Event",
        eventDescription = "This is a description of the sample event.",
        eventImage = painterResource(id = R.drawable.foto_seminar)
    )
}