@file:Suppress("DEPRECATION")

package com.example.evelin

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import com.example.evelin.ui.login.LoginScreen
import com.example.evelin.ui.register.RegisterScreen
import com.example.evelin.ui.home.HomeScreen
import com.example.evelin.data.UserPreferences
import com.example.evelin.ui.addEvent.AddEventScreen
import com.example.evelin.ui.eventDetail.EventDetailsScreen
import com.example.evelin.ui.history.EventHistoryScreen
import com.example.evelin.ui.profile.ProfileScreen
import com.example.evelin.ui.registerEvent.RegisterEventScreen
import com.example.evelin.ui.userInfo.UserInfoScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import kotlinx.coroutines.flow.first

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EvelinApp()
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
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


    AnimatedNavHost(navController = navController, startDestination = if (isLoggedIn) "home" else "login") {
        composable(
            "login",
            enterTransition = { fadeIn() },
            exitTransition = { fadeOut() }
        ) { LoginScreen(context, navController) }
        composable(
            "register",
            enterTransition = { fadeIn() },
            exitTransition = { fadeOut() }
        ) { RegisterScreen(navController) }
        composable(
            "home",
            enterTransition = { fadeIn() },
            exitTransition = { fadeOut() }
        ) { HomeScreen(navController = navController) }
        composable(
            "profile",
            enterTransition = { fadeIn() },
            exitTransition = { fadeOut() }
        ) { ProfileScreen(navController) }
        composable(
            "addEvent",
            enterTransition = { fadeIn() },
            exitTransition = { fadeOut() }
        ) { AddEventScreen(navController) }
        composable(
            "userInfo",
            enterTransition = { fadeIn() },
            exitTransition = { fadeOut() }
        ) { UserInfoScreen(onBackClick = { navController.popBackStack() }) }
        composable(
            "eventHistory",
            enterTransition = { fadeIn() },
            exitTransition = { fadeOut() }
        ) { EventHistoryScreen(onBackClick = { navController.popBackStack() }) }
        composable(
            "eventDetails",
            enterTransition = { fadeIn() },
            exitTransition = { fadeOut() }
        ) { EventDetailsScreen(navController = navController) }
        composable(
            "registerEvent",
            enterTransition = { fadeIn() },
            exitTransition = { fadeOut() }
        ) { RegisterEventScreen(onBackClick = { navController.popBackStack() }) }
    }
}