package com.example.evelin

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.evelin.ui.login.LoginScreen
import com.example.evelin.ui.register.RegisterScreen
import com.example.evelin.ui.home.HomeScreen
import com.example.evelin.data.UserPreferences
import kotlinx.coroutines.flow.first

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EvelinApp()
        }
    }
}

@Composable
fun EvelinApp() {
    val navController = rememberNavController()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        val token = UserPreferences.getToken(context).first()
        val isLoggedIn = UserPreferences.isLoggedIn(context)
        if (token != null) {
            UserPreferences.saveLoginStatus(context, true)
        }
        Log.d("EvelinApp", "Token: $token, isLoggedIn: $isLoggedIn")

    }

    val isLoggedIn = remember { UserPreferences.isLoggedIn(context) }
    Log.d("EvelinApp", "isLoggedIn: $isLoggedIn")

    LaunchedEffect(isLoggedIn) {
        if (isLoggedIn) {
            navController.navigate("home") {
                popUpTo("login") { inclusive = true }
            }
        } else {
            navController.navigate("login") {
                popUpTo("home") { inclusive = true }
            }
        }
    }

    NavHost(navController = navController, startDestination = if (isLoggedIn) "home" else "login") {
        composable("login") { LoginScreen(context,navController) }
        composable("register") { RegisterScreen(navController) }
        composable("home") { HomeScreen() }
    }
}