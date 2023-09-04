package com.compfest.mediscanapp.paging

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.compfest.mediscanapp.ui.article.ViewModelArticle
import com.compfest.mediscanapp.ui.scancamera.HandWritinModel

class ModelFactory private constructor(private val searchPagingRepo: SearchPagingRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(SearchPagingModel::class.java) -> {
                SearchPagingModel(searchPagingRepo) as T
            }
            modelClass.isAssignableFrom(ViewModelArticle::class.java) -> {
                ViewModelArticle(searchPagingRepo) as T
            }
            modelClass.isAssignableFrom(HandWritinModel::class.java) -> {
                HandWritinModel(searchPagingRepo) as T
            }
            else -> throw IllegalArgumentException("Unknown Model class: " + modelClass.name)
        }
    }

    companion object{
        @Volatile
        private var instance: ModelFactory? = null
        fun getInstance(context: Context): ModelFactory =
            instance ?: synchronized(this) {
                instance ?: ModelFactory(Injection.provideRepository(context))
            }.also { instance = it }
    }
}