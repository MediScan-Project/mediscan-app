package com.compfest.mediscanapp.api.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


data class ModelDrugResponse(

	@field:SerializedName("predictions")
	val predictions: List<PredictionsItemItem>
)

@Parcelize
data class PredictionsItemItem(

	@field:SerializedName("confidence")
	val confidence: String,

	@field:SerializedName("class_name")
	val className: String
):Parcelable
