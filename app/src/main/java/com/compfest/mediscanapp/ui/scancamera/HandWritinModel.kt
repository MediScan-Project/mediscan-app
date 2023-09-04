package com.compfest.mediscanapp.ui.scancamera

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.compfest.mediscanapp.api.response.ModelDrugResponse
import com.compfest.mediscanapp.api.response.PredictionsItemItem
import com.compfest.mediscanapp.paging.SearchPagingRepository
import kotlinx.coroutines.launch
import okhttp3.MultipartBody


class HandWritinModel(private val repository: SearchPagingRepository) : ViewModel() {
    val upload: LiveData<ModelDrugResponse> = repository.upload

    fun uploadStory(file: MultipartBody.Part) {
        viewModelScope.launch {
            repository.uploadHandWriting(file)
        }
    }

}