@file:Suppress("DEPRECATION")

package com.example.evelin.ui.addEvent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.evelin.ui.component.utils.DatePickerField
import com.example.evelin.ui.component.utils.UploadMedia
import com.example.evelin.ui.theme.Green
import com.example.evelin.ui.theme.LightGreen
import com.example.evelin.R

@Composable
fun AddEventScreen(navController: NavController) {
    var judulAcara by remember { mutableStateOf("") }
    var deskripsiAcara by remember { mutableStateOf("") }
    var tanggalWaktu by remember { mutableStateOf("") }
    var lokasi by remember { mutableStateOf("") }
    var kategoriAcara by remember { mutableStateOf("") }
    var pembicara by remember { mutableStateOf("") }
    var insert by remember { mutableStateOf("") }
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .background(Color.White)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(painter = painterResource(id = R.drawable.ic_back), contentDescription = "Back")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Back",
                fontSize = 20.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Input Event",
            fontSize = 28.sp,
            color = Green,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.Start)
        )

        Spacer(modifier = Modifier.height(16.dp))

        InputField(label = "Judul Acara*", value = judulAcara) { judulAcara = it }
        InputField(label = "Deskripsi Acara*", value = deskripsiAcara) { deskripsiAcara = it }
        DatePickerField(label = "Tanggal & Waktu*", selectedDate = tanggalWaktu) { tanggalWaktu = it }
        InputField(label = "Lokasi*", value = lokasi) { lokasi = it }
        InputField(label = "Kategori Acara*", value = kategoriAcara) { kategoriAcara = it }
        InputField(label = "Pembicara/Guest Star (Opsional)", value = pembicara) { pembicara = it }
        InputField(label = "Insert (Opsional)", value = insert) { insert = it }

        UploadMedia()

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { /* Handle publish logic */ },
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .height(50.dp),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Green)
        ) {
            Text(
                text = "Publish",
                color = Color.White,
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 20.sp
            )
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputField(
    label: String,
    value: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    trailingIcon: @Composable (() -> Unit)? = null,
    onValueChange: (String) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = label,
            color = Color.Black,
            style = MaterialTheme.typography.bodyMedium,
            fontSize = 14.sp
        )
        Spacer(modifier = Modifier.height(4.dp))
        TextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .background(LightGreen, RoundedCornerShape(8.dp)),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            trailingIcon = trailingIcon,
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Green,
                unfocusedIndicatorColor = Color.Gray,
                focusedContainerColor = LightGreen,
                unfocusedContainerColor = LightGreen
            )
        )
    }
    Spacer(modifier = Modifier.height(12.dp))
}



@Preview(showBackground = true)
@Composable
fun AddEventScreenPreview() {
    val navController = rememberNavController()
    AddEventScreen(navController = navController)
}
