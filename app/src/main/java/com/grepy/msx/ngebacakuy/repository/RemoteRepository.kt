package com.grepy.msx.ngebacakuy.repository

import androidx.lifecycle.LiveData
import com.grepy.msx.ngebacakuy.model.Book
import com.grepy.msx.ngebacakuy.model.Category

interface RemoteRepository {
    suspend fun fetchDataUpToDate()
    fun getDataUpToDte() : LiveData<MutableList<Book>>
    suspend fun fetchCategory()
    fun getCategory() : LiveData<MutableList<Category>>
}