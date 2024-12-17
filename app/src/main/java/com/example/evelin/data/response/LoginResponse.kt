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

data class LogoutResponse(
	@SerializedName("success") val success: Boolean,
	@SerializedName("message") val message: String
)

data class UserResponse(
	@SerializedName("id") val id: Int,
	@SerializedName("name") val name: String,
	@SerializedName("email") val email: String,
	@SerializedName("noHp") val noHp: String,
	@SerializedName("institusi") val institusi: String
)