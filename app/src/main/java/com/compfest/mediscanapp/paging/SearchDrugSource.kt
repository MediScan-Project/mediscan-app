package com.compfest.mediscanapp.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.compfest.mediscanapp.api.ApiService
import com.compfest.mediscanapp.api.response.DataItem


class SearchDrugSource(private val apiService: ApiService, private val query: String) : PagingSource<Int, DataItem>() {

    private companion object {
        const val INITIAL_PAGE_INDEX = 1
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DataItem> {
        return try {
            val page = params.key ?: INITIAL_PAGE_INDEX
            run {
                val responseData = apiService.searchDrug(query, page, params.loadSize)
                Log.d("dataPaging", "$responseData")

                LoadResult.Page(
                    data = responseData.data,
                    prevKey = if (page == 1) null else page - 1,
                    nextKey = if (responseData.data.isEmpty()) null else page + 1
                )
            }
        } catch (exception: Exception) {
            Log.d("pagingError", exception.toString())
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, DataItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}