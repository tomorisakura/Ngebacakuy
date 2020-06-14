package com.grepy.msx.ngebacakuy.repository.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.grepy.msx.ngebacakuy.model.Book
import com.grepy.msx.ngebacakuy.model.Detail
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BookResponse(
    @SerializedName("result") var result : ArrayList<Book>
) : Parcelable


data class DetailsBookResponse(
    @SerializedName("result") var result: Detail
)