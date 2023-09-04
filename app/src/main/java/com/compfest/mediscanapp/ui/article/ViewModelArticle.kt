package com.compfest.mediscanapp.ui.article

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.compfest.mediscanapp.api.response.ArticleDrugResponse
import com.compfest.mediscanapp.api.response.DataArtikelItem
import com.compfest.mediscanapp.paging.SearchPagingRepository

class ViewModelArticle(storyRepository: SearchPagingRepository) : ViewModel() {
    val listArticle: LiveData<ArticleDrugResponse> = storyRepository.listArticle

    val pagingStory: LiveData<PagingData<DataArtikelItem>> =
        storyRepository.getArticlePaging().cachedIn(viewModelScope)

    fun logModel() {
        Log.d("pagingModel", "$pagingStory")
    }
}