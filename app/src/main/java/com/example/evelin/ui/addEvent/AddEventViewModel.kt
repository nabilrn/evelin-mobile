package com.example.evelin.ui.addEvent

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.evelin.data.UserRepository
import com.example.evelin.data.response.AddEventResponse
import com.example.evelin.data.response.DataItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext
import okhttp3.MultipartBody
import okhttp3.RequestBody

class AddEventViewModel (private val repository: UserRepository) : ViewModel() {
    private val _events = MutableStateFlow<DataItem?>(null)
    val events: StateFlow<DataItem?> = _events.asStateFlow()

    suspend fun addEvent(
        title: RequestBody,
        description: RequestBody,
        eventDate: RequestBody,
        location: RequestBody,
        category: RequestBody,
        posterUrl: MultipartBody.Part
    ): AddEventResponse {
//        _isLoading.value = true
        return try {
            repository.addEvent(title, description, eventDate, location, category, posterUrl ).also {
            }
        } catch (e: Exception) {
            throw e
        } finally {
//            _isLoading.value = false
        }
    }
}

