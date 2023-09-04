package com.compfest.mediscanapp.paging

import android.content.Context
import com.compfest.mediscanapp.api.ApiConfig

object Injection {

    fun provideRepository(context: Context): SearchPagingRepository {
        val apiService = ApiConfig.getApiService()
        return SearchPagingRepository.getInstace(apiService)
    }
}