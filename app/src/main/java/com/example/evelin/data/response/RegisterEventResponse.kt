package com.example.evelin.data.response

import com.google.gson.annotations.SerializedName

data class RegisterEventResponse(

	@field:SerializedName("data")
	val data: Data? = null,

	@field:SerializedName("error")
	val error: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class UpdatedAt(

	@field:SerializedName("val")
	val val: String? = null
)

data class CreatedAt(

	@field:SerializedName("val")
	val val: String? = null
)

data class Data(

	@field:SerializedName("createdAt")
	val createdAt: CreatedAt? = null,

	@field:SerializedName("eventId")
	val eventId: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("userId")
	val userId: Int? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: UpdatedAt? = null
)
