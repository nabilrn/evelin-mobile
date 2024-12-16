package com.example.evelin.data.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(

	@field:SerializedName("data")
	val data: Data,

	@field:SerializedName("error")
	val error: Boolean,

	@field:SerializedName("message")
	val message: String
)

data class User(

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("email")
	val email: String
)

data class Data(

	@field:SerializedName("user")
	val user: User,

	@field:SerializedName("token")
	val token: String,

	@field:SerializedName("refreshToken")
	val refreshToken: String
)