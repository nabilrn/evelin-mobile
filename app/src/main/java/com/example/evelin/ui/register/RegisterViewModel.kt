package com.example.evelin.ui.register

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.evelin.data.remote.ApiConfig
import com.example.evelin.data.request.RegisterRequest
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {

    fun register(
        context: Context,
        name: String,
        email: String,
        phone: String,
        institute: String,
        password: String,
        passwordConfirmation: String,
        onResult: (Boolean) -> Unit
    ) {
        viewModelScope.launch {
            try {
                val request = RegisterRequest(
                    name = name,
                    email = email,
                    noHp = phone,
                    institusi = institute,
                    password = password,
                    password_confirmation = passwordConfirmation
                )
                val response = ApiConfig.api.register(request)
                if (!response.error) {
                    Toast.makeText(context, "Register success", Toast.LENGTH_SHORT).show()
                    onResult(true)
                } else {
                    Toast.makeText(context, response.message, Toast.LENGTH_SHORT).show()
                    onResult(false)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(context, "Register failed: ${e.message}", Toast.LENGTH_SHORT).show()
                onResult(false)
            }
        }
    }
}