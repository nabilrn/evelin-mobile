package com.example.evelin.data

import com.example.evelin.data.pref.UserModel
import com.example.evelin.data.pref.UserPreference

import com.example.evelin.data.remote.ApiService
import com.example.evelin.data.request.LoginRequest
import com.example.evelin.data.request.RegisterRequest
import com.example.evelin.data.response.DataUser
import com.example.evelin.data.response.EventsResponse
import com.example.evelin.data.response.LoginResponse
import com.example.evelin.data.response.RegisterResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.HttpException

class UserRepository private constructor(
    private val apiService: ApiService,
    private val userPreference: UserPreference
) {


    suspend fun login(email: String, password: String): LoginResponse {
        return apiService.login(LoginRequest(email,password))
    }

    suspend fun saveSession(user: UserModel) {
        userPreference.saveSession(user)
    }

    fun getSession(): Flow<UserModel> {
        return userPreference.getSession()
    }

    suspend fun logout() {
        userPreference.logout()
    }

    private fun getToken(): String {
        return runBlocking { userPreference.getSession().first().token }
    }
    suspend fun register(request: RegisterRequest): RegisterResponse {
        return try {
            apiService.register(request)
        } catch (e: HttpException) {
            throw e
        }
    }


    suspend fun getUser(): DataUser {
        val token = getToken()
        try {
            return apiService.getUser("Bearer $token").dataUser
        } catch (e: HttpException) {
            if (e.code() == 401) {
                logout()
                throw e
            } else {
                throw e
            }
        }
    }




    suspend fun getEvents(): EventsResponse {
        val token = getToken()
        try {
            return apiService.getEvents("Bearer $token")
        } catch (e: HttpException) {
            if (e.code() == 401) {
                logout()
                throw e
            } else {
                throw e
            }
        }
    }
//
//    suspend fun getMeasure(id: String): MeasureResponse {
//        val token = getToken()
//        return apiService.getMeasure("Bearer $token", id)
//    }
//
//    suspend fun addMeasure(
//        baby_photo_url: MultipartBody.Part,
//        levelActivity: RequestBody,
//        statusAsi: RequestBody?,
//        age: RequestBody?,
//        weight: RequestBody?,
//        date: RequestBody?
//    ): AddMeasureResponse {
//        val token = getToken()
//
//        return apiService.addMeasure("Bearer $token", baby_photo_url, levelActivity, statusAsi, age, weight, date)
//    }


    companion object {
        @Volatile
        private var instance: UserRepository? = null
        fun getInstance(
            apiService: ApiService,
            userPreference: UserPreference
        ): UserRepository =
            instance ?: synchronized(this) {
                instance ?: UserRepository(apiService, userPreference)
            }.also { instance = it }
    }
}