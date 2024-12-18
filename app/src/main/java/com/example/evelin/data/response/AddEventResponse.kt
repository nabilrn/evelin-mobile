package com.example.evelin.data.response

import com.google.gson.annotations.SerializedName

data class AddEventResponse(

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

data class Data(

	@field:SerializedName("createdAt")
	val createdAt: CreatedAt? = null,

	@field:SerializedName("posterUrl")
	val posterUrl: String? = null,

	@field:SerializedName("university")
	val university: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("location")
	val location: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("category")
	val category: String? = null,

	@field:SerializedName("userId")
	val userId: Int? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: UpdatedAt? = null,

	@field:SerializedName("eventDate")
	val eventDate: String? = null
)

data class CreatedAt(

	@field:SerializedName("val")
	val val: String? = null
)
