package com.compfest.mediscanapp.api

import com.compfest.mediscanapp.api.response.*
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @GET("/search")
    suspend fun searchDrug(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): SearchDrugResponse

    @GET("/articles")
    suspend fun articleDrug(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): ArticleDrugResponse

    @Multipart
    @POST("/receipt")
    fun uploadHandWriting(
        @Part file: MultipartBody.Part,
    ): Call<ModelDrugResponse>

    @Multipart
    @POST("/ocr")
    fun uploadDrug(
        @Part file: MultipartBody.Part,
    ): Call<ModelDrugResponse>

}