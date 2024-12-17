package com.example.evelin.ui.profile

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.evelin.data.UserPreferences
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is profile Fragment"
    }
    val text: LiveData<String> = _text

    fun logout(context: Context, onSuccess:()->Unit) {
        viewModelScope.launch {
            UserPreferences.clearToken(context)
            UserPreferences.saveLoginStatus(context, false)
            onSuccess()
        }
    }
}