package com.grepy.msx.ngebacakuy.repository

import androidx.lifecycle.LiveData
import com.grepy.msx.ngebacakuy.model.Book

interface RemoteRepository {
    suspend fun fetchDataUpToDate()
    fun getDataUpToDte() : LiveData<MutableList<Book>>
}