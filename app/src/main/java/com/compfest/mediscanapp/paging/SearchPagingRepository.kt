package com.compfest.mediscanapp.paging

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.compfest.mediscanapp.api.ApiConfig
import com.compfest.mediscanapp.api.ApiService
import com.compfest.mediscanapp.api.response.*
import com.compfest.mediscanapp.ui.article.ArticleDrugSource
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SearchPagingRepository(
    private val apiService: ApiService,
) {
    private val _listArticle = MutableLiveData<ArticleDrugResponse>()
    val listArticle: LiveData<ArticleDrugResponse> = _listArticle

    private val _upload = MutableLiveData<ModelDrugResponse>()
    val upload: LiveData<ModelDrugResponse> = _upload

    fun getDrugPaging(query: String): LiveData<PagingData<DataItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 5
            ),
            pagingSourceFactory = {
                SearchDrugSource(apiService, query)
            }
        ).liveData
    }

    fun getArticlePaging(): LiveData<PagingData<DataArtikelItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 5
            ),
            pagingSourceFactory = {
                ArticleDrugSource(apiService)
            }
        ).liveData
    }

    fun uploadHandWriting(file: MultipartBody.Part) {
        val client = ApiConfig.getApiService().uploadHandWriting(file)
        client.enqueue(object : Callback<ModelDrugResponse> {
            override fun onResponse(
                call: Call<ModelDrugResponse>,
                response: Response<ModelDrugResponse>
            ) {
                if (response.isSuccessful) {

                    Log.e("storyResponse", "onResponse: ${response.message()}")
                    _upload.value = response.body()
                } else {
                    Log.e("postStory", "onFailure: ${response.message()}")
                    Log.e("postStory", "onFailure: ${response.errorBody()}")
                    Log.e("postStory", "onFailure: ${response.code()}")
                }
            }
            override fun onFailure(call: Call<ModelDrugResponse>, t: Throwable) {
                Log.e("PostStoryFailure", "onFailure: ${t.message}")
                Log.e("PostStoryFailure", "onFailure: ${t.cause}")
                Log.e("PostStoryFailure", "onFailure: ${t.localizedMessage}")
            }
        })
    }

    companion object {
        @Volatile
        private var instance: SearchPagingRepository? = null
        fun getInstace(apiService: ApiService): SearchPagingRepository = instance ?: synchronized(this){
            instance ?: SearchPagingRepository(apiService)
        }.also { instance= it }
    }
}
