package com.grepy.msx.ngebacakuy.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONArrayRequestListener
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.google.gson.GsonBuilder
import com.grepy.msx.ngebacakuy.constant.Constant
import com.grepy.msx.ngebacakuy.model.Book
import com.grepy.msx.ngebacakuy.repository.RemoteRepository
import com.grepy.msx.ngebacakuy.repository.response.BookResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject

class HomeViewModel : ViewModel(), RemoteRepository {

    private lateinit var bookResponse : BookResponse
    private var newBook : MutableList<Book> = mutableListOf()
    private var bookLiveData : MutableLiveData<MutableList<Book>> = MutableLiveData()

    override suspend fun fetchDataUpToDate() {
        withContext(Dispatchers.IO) {
            AndroidNetworking.get(Constant.BASE_URL_NEW_BOOK+Constant.HEADERS)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(object : JSONObjectRequestListener{

                    override fun onResponse(response: JSONObject?) {
                        bookResponse = GsonBuilder().create().fromJson(response.toString(), BookResponse::class.java)
                        newBook.clear()
                        bookResponse.result.forEach {
                            newBook.add(it)
                        }
                        bookLiveData.postValue(newBook)
                    }

                    override fun onError(anError: ANError) {
                        Log.d(Constant.FAIL_TAG, anError.message.toString())
                    }

                })
        }
    }

    override fun getDataUpToDte(): LiveData<MutableList<Book>> {
        GlobalScope.launch(Dispatchers.IO) {
            fetchDataUpToDate()
        }
        return bookLiveData
    }
}