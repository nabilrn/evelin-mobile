@file:Suppress("DEPRECATION")

package com.example.evelin.ui.registerEvent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.shape.RoundedCornerShape
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Text
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.evelin.R
import com.example.evelin.ui.component.utils.UploadMedia
import com.example.evelin.ui.theme.Green
import com.example.evelin.ui.theme.LightGreen

@Composable
fun RegisterEventScreen(onBackClick: () -> Unit = {}) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var institution by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Back Button (Optional)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            contentAlignment = Alignment.TopStart
        ) {
            IconButton(onClick = onBackClick) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = "Back",
                    tint = Green
                )
            }
        }

        // Register Now Title
        Text(
            text = "Register Now!",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Green,
            textAlign = TextAlign.Center
        )
        Text(
            text = "to be a part of the Event.\nFill the information carefully",
            fontSize = 14.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 8.dp, bottom = 24.dp)
        )

        // Input Fields
        InputField(label = "Nama", value = name) { name = it }
        Spacer(modifier = Modifier.height(8.dp))
        InputField(label = "Email*", value = email) { email = it }
        Spacer(modifier = Modifier.height(8.dp))
        InputField(label = "No. Telepon*", value = phoneNumber, keyboardType = KeyboardType.Phone) { phoneNumber = it }
        Spacer(modifier = Modifier.height(8.dp))
        InputField(label = "Asal Institusi*", value = institution) { institution = it }
        Spacer(modifier = Modifier.height(8.dp))

        // Upload Media for Bukti Pembayaran
        UploadMedia()

        Spacer(modifier = Modifier.height(32.dp))

        // Submit Button
        Button(
            onClick = { /* Handle Submit */ },
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Green),
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {
            Text(
                text = "SUBMIT",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
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
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Green,
                unfocusedIndicatorColor = Color.Gray,
                containerColor = LightGreen
            )
        )
    }
    Spacer(modifier = Modifier.height(12.dp))
}

@Preview(showBackground = true)
@Composable
fun PreviewRegisterEventScreen() {
    RegisterEventScreen()
}