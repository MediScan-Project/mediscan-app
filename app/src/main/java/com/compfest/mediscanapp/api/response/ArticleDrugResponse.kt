package com.compfest.mediscanapp.api.response

import com.google.gson.annotations.SerializedName

data class ArticleDrugResponse(

	@field:SerializedName("dataArtikel")
	val dataArtikel: List<DataArtikelItem>
)

data class DataArtikelItem(

	@field:SerializedName("image")
	val image: String,

	@field:SerializedName("link")
	val link: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("content")
	val content: String
)
