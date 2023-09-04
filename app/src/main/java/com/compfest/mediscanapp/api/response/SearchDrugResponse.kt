package com.compfest.mediscanapp.api.response

import com.google.gson.annotations.SerializedName

data class SearchDrugResponse(

	@field:SerializedName("data")
	val data: List<DataItem>,

	@field:SerializedName("success")
	val success: Boolean,

	@field:SerializedName("message")
	val message: String
)

data class DataItem(

	@field:SerializedName("komposisi")
	val komposisi: String,

	@field:SerializedName("nomorIzin")
	val nomorIzin: String,

	@field:SerializedName("manfaat")
	val manfaat: String,

	@field:SerializedName("dosis")
	val dosis: String,

	@field:SerializedName("gambarObat")
	val gambarObat: String,

	@field:SerializedName("golonganObat")
	val golonganObat: String,

	@field:SerializedName("kemasan")
	val kemasan: String,

	@field:SerializedName("efekSamping")
	val efekSamping: String,

	@field:SerializedName("namaObat")
	val namaObat: String,

	@field:SerializedName("penyajian")
	val penyajian: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("resepDokter")
	val resepDokter: String,

	@field:SerializedName("deskripsi")
	val deskripsi: String
)
