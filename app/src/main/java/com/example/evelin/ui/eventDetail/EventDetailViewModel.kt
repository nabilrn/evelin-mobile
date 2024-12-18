package com.example.evelin.ui.eventDetail

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.evelin.data.UserRepository
import com.example.evelin.data.response.DataItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext

class EventDetailViewModel(private val repository: UserRepository) : ViewModel() {
    private val _events = MutableStateFlow<DataItem?>(null)
    val events: StateFlow<DataItem?> = _events.asStateFlow()

    suspend fun getEvent(id: String): DataItem? {
        try {
            val response = withContext(Dispatchers.IO) { repository.getEvent(id) }
            _events.value = response.data
            return response.data
        } catch (e: Exception) {
            _events.value = null // Handle error case
            Log.e("DetailViewModel", "Error fetching stories: ${e.message}", e)
            return null
        }
    }
}