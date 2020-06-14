package com.grepy.msx.ngebacakuy.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Writer(
    @SerializedName("id") val id : Int,
    @SerializedName("user_id") val user_id : Int,
    @SerializedName("created_at") val created_at : String,
    @SerializedName("kelas") val kelas : String?,
    @SerializedName("status") val status : String?,
    @SerializedName("schedule_task") val scheduleTask : String,
    @SerializedName("royalty_id") val royalty : String?,
    @SerializedName("type") val type : String,
    @SerializedName("User_by_user_id") val userByUser : User
) : Parcelable

@Parcelize
data class User(
    @SerializedName("id") val id : Int,
    @SerializedName("name") val name : String
) : Parcelable

@Parcelize
data class NewWriter(
    @SerializedName("id") val id : Int,
    @SerializedName("user_id") val user_id : Int,
    @SerializedName("User_by_user_id") val userByUser : NewUser
) : Parcelable

@Parcelize
data class NewUser(
    @SerializedName("id") val id : Int,
    @SerializedName("name") val name : String,
    @SerializedName("photo_url") val photoUrl : String?
) : Parcelable