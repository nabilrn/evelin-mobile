// HomeScreen.kt
package com.example.evelin.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.evelin.ui.component.bar.BottomNavBar
import com.example.evelin.ui.component.bar.TopBar
import com.example.evelin.ui.component.item.EventItem
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.evelin.R

@Composable
fun HomeScreen(modifier: Modifier = Modifier, navController: NavController) {
    Column(modifier = modifier.fillMaxSize()) {
        TopBar(onFilterClick = { /* Handle filter click */ })
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Upcoming Event", modifier = Modifier.padding(start = 16.dp))
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            items(listOf("Event 1", "Event 2", "Event 3")) { event ->
                EventItem(
                    eventName = event,
                    eventDescription = "Description for $event",
                    eventImage = painterResource(id = R.drawable.reza_kecap)
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Recommendation Event", modifier = Modifier.padding(start = 16.dp))
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            items(listOf("Event A", "Event B", "Event C")) { event ->
                EventItem(
                    eventName = event,
                    eventDescription = "Description for $event",
                    eventImage = painterResource(id = R.drawable.reza_kecap)
                )
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        BottomNavBar(navController = navController)
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    val navController = rememberNavController()
    HomeScreen(navController = navController)
}