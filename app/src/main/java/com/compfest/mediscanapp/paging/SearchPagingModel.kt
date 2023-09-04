package com.compfest.mediscanapp.paging

import androidx.lifecycle.*
import androidx.paging.cachedIn


class SearchPagingModel(searchRepository: SearchPagingRepository) : ViewModel() {
    private val currentQuery = MutableLiveData(DEFAULT_QUERY)

    val pagingDrug = currentQuery.switchMap { queryString ->
        searchRepository.getDrugPaging(queryString).cachedIn(viewModelScope)
    }

    fun searchDrug(query: String) {
        currentQuery.value = query
    }

    companion object {
        private const val DEFAULT_QUERY = "A"
    }

}

