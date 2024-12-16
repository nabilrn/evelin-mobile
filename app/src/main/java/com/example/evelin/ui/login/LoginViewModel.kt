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
                val response = ApiConfig.api.login(LoginRequest(email, password))
                UserPreferences.saveToken(context, response.data.token)
                UserPreferences.saveLoginStatus(context, true)
                onResult(true)
            } catch (e: Exception) {
                onResult(false)
            }
        }
    }
}