package com.example.evelin.data.remote

import com.example.evelin.data.request.LoginRequest
import com.example.evelin.data.request.RegisterRequest
import com.example.evelin.data.response.LoginResponse
import com.example.evelin.data.response.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("/login")
    suspend fun login(@Body request: LoginRequest): LoginResponse

    @POST("/register")
    suspend fun register(@Body request: RegisterRequest): RegisterResponse

}