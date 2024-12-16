@file:Suppress("DEPRECATION")

package com.example.evelin.ui.userInfo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.evelin.R
import com.example.evelin.ui.theme.Blue
import com.example.evelin.ui.theme.Green
import com.example.evelin.ui.theme.LightGreen

@Composable
fun UserInfoScreen(onBackClick: () -> Unit) {
    Scaffold(
        backgroundColor = Color.White
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Back Icon
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBackClick) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_back),
                        contentDescription = "Back",
                        tint = Color.Black
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Back",
                    fontSize = 20.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
            }

            // Header Text
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Informasi\nProfile",
                fontSize = 32.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                lineHeight = 36.sp
            )

            // Profile Information Section
            Spacer(modifier = Modifier.height(32.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .background(LightGreen, RoundedCornerShape(8.dp))
                    .padding(16.dp)
            ) {
                Column {
                    ProfileInfoItem(label = "Full Name", value = "Nabillah R. Dzakira")
                    ProfileInfoItem(label = "Nick Name", value = "Nabillah")
                    ProfileInfoItem(label = "Email", value = "nbilardzkr20@gmail.com", isLink = true)
                    ProfileInfoItem(
                        label = "Password",
                        value = "**********",
                        changeAction = "Change password"
                    )
                    ProfileInfoItem(label = "Nomor Telepon", value = "+6281286823201")
                    ProfileInfoItem(label = "Asal Institusi", value = "Universitas Andalas")
                }
            }
        }
    }
}

@Composable
fun ProfileInfoItem(label: String, value: String, isLink: Boolean = false, changeAction: String? = null) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Text(
            text = label,
            fontSize = 12.sp,
            color = Color.Gray,
            fontWeight = FontWeight.SemiBold
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (isLink) {
                ClickableText(
                    text = AnnotatedString(value),
                    onClick = {},
                    style = LocalTextStyle.current.copy(
                        color = Blue,
                        fontSize = 14.sp
                    )
                )
            } else {
                Text(
                    text = value,
                    fontSize = 14.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Normal
                )
            }
            if (changeAction != null) {
                ClickableText(
                    text = AnnotatedString(changeAction),
                    onClick = {},
                    style = LocalTextStyle.current.copy(
                        color = Green,
                        fontSize = 12.sp
                    )
                )
            }
        }
        Divider(
            color = Color.LightGray,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun UserInfoScreenPreview() {
    UserInfoScreen(onBackClick = {})
}