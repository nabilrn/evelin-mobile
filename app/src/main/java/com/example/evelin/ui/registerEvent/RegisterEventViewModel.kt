package com.example.evelin.ui.registerEvent

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.evelin.data.UserRepository
import com.example.evelin.data.response.DataItem
import com.example.evelin.data.response.DataUser
import com.example.evelin.data.response.LoginUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegisterEventViewModel (private val repository: UserRepository) : ViewModel() {

    private val _user = MutableStateFlow<LoginUser?>(null)
    val user: StateFlow<LoginUser?> = _user.asStateFlow()


    private val _registrationStatus = MutableStateFlow<String?>(null)
    val registrationStatus: StateFlow<String?> = _registrationStatus.asStateFlow()


//    fun registerEvent( eventId: String) {
//        repository.registerEvent( eventId)
//    }

    fun fetchUser() {
        viewModelScope.launch {
            try {
                val response = repository.getUser()

                _user.value = response
                Log.d("RegisterEventViewModel", "User data fetched: $response")
            } catch (e: Exception) {
                _user.value = null
                // Log error or handle appropriately
            }
        }
    }

    fun submitEventRegistration(
        eventId: String
    ) {
        viewModelScope.launch {
            try {
                // Implement your specific event registration logic
                val result = repository.submitEventRegistration(
                    eventId = eventId
                )
                _registrationStatus.value = result.message
            } catch (e: Exception) {
                _registrationStatus.value = e.message
                // Log error or handle appropriately
            }
        }
    }
}