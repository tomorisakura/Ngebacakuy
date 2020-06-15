package com.grepy.msx.ngebacakuy.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Category(
    @SerializedName("id") val id : Int,
    @SerializedName("title") val title : String,
    @SerializedName("icon_url") val icons : String?,
    @SerializedName("count") val count : Int
) : Parcelable