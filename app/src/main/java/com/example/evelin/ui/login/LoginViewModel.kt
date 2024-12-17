package com.example.evelin.ui.login

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.evelin.data.UserPreferences
import com.example.evelin.data.remote.ApiConfig
import com.example.evelin.data.request.LoginRequest
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    fun login(context: Context, email: String, password: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                // Tambahkan log untuk cek input
                println("Login attempt: $email")

                val response = ApiConfig.api.login(LoginRequest(email, password))

                // Tambahkan log untuk cek response
                println("Login response: $response")

                UserPreferences.saveToken(context, response.data.token.toString())
                UserPreferences.saveLoginStatus(context, true)
                onResult(true)
            } catch (e: Exception) {
                // Print detail error
                e.printStackTrace()
                println("Login error: ${e.message}")
                onResult(false)
            }
        }
    }
}