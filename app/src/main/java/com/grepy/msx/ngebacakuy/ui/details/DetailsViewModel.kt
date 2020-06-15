package com.grepy.msx.ngebacakuy.ui.details

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
import com.grepy.msx.ngebacakuy.model.Detail
import com.grepy.msx.ngebacakuy.model.RelatedBook
import com.grepy.msx.ngebacakuy.repository.response.DetailsBookResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject

class DetailsViewModel : ViewModel() {

    private lateinit var detailsBookResponse: DetailsBookResponse
    private var detailList : MutableList<Detail> = mutableListOf()
    private var relatedBook : MutableList<RelatedBook> = mutableListOf()
    private var detailLiveData : MutableLiveData<MutableList<Detail>> = MutableLiveData()
    private var relatedBookLiveData : MutableLiveData<MutableList<RelatedBook>> = MutableLiveData()

    private suspend fun fetchDataDetails(query : Int) {
        withContext(Dispatchers.IO) {
            AndroidNetworking.get(Constant.BOOK_DETAILS+query)
                .addHeaders(Constant.X_HEADER, Constant.HEADERS)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(object : JSONObjectRequestListener{
                    override fun onResponse(response: JSONObject?) {
                        detailsBookResponse = GsonBuilder().create().fromJson(response.toString(), DetailsBookResponse::class.java)
                        detailsBookResponse.result.let {
                            detailList.addAll(arrayListOf(it))
                            if (it.relatedBook.isNotEmpty()) {
                                relatedBook.addAll(it.relatedBook)
                            }
                        }
                        relatedBookLiveData.postValue(relatedBook)
                        detailLiveData.postValue(detailList)
                    }

                    override fun onError(anError: ANError?) {
                        Log.d(Constant.FAIL_TAG, anError?.message.toString())
                    }

                })
        }
    }

    internal fun getDetailData(query: Int) : LiveData<MutableList<Detail>> {
        GlobalScope.launch(Dispatchers.IO) {
            fetchDataDetails(query)
        }
        return detailLiveData
    }
}