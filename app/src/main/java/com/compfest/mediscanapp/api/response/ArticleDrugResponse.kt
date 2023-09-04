package com.compfest.mediscanapp.api.response

import com.google.gson.annotations.SerializedName

data class ArticleDrugResponse(

	@field:SerializedName("dataArtikel")
	val dataArtikel: List<DataArtikelItem>
)

data class DataArtikelItem(

	@field:SerializedName("artikel")
	val artikel: String,

	@field:SerializedName("referensi")
	val referensi: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("judul")
	val judul: String,

	@field:SerializedName("gambar")
	val gambar: String
)
