package com.example.evelin.ui.component.bar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.example.evelin.R
import com.example.evelin.ui.theme.Green
import com.example.evelin.ui.theme.LightGray

@Composable
fun TopBar(
    onFilterClick: () -> Unit // Lambda to handle filter button click
) {
    var searchText by remember { mutableStateOf(TextFieldValue("")) }

    // Top App Bar
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(horizontal = 8.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Search Field
        Box(
            modifier = Modifier
                .weight(1f)
                .height(48.dp)
                .clip(CircleShape)
                .background(LightGray) // Light gray background
                .padding(horizontal = 16.dp, vertical = 8.dp),
        ) {
            if (searchText.text.isEmpty()) {
                Text(
                    text = "Search...",
                    color = Color.Gray,
                    fontSize = 14.sp,
                    modifier = Modifier.align(Alignment.CenterStart)
                )
            }
            BasicTextField(
                value = searchText,
                onValueChange = { searchText = it },
                textStyle = LocalTextStyle.current.copy(color = Color.Black, fontSize = 14.sp),
                modifier = Modifier.fillMaxSize()
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        // Filter Button
        IconButton(
            onClick = { onFilterClick() },
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(Green) // Green background for filter button
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_filter), // Replace with filter icon
                contentDescription = "Filter",
                tint = Color.White
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TopBarWithSearchAndFilterPreview() {
    TopBar(onFilterClick = { /* Handle filter click */ })
}
