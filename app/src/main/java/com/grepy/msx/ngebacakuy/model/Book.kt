package com.grepy.msx.ngebacakuy.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Book(
    @SerializedName("id") val id : Int,
    @SerializedName("title") val titleBook : String,
    @SerializedName("writer_id") val writerId : Int,
    @SerializedName("cover_url") val coverUrl : String,
    @SerializedName("created_at") val createdAt : String,
    @SerializedName("status") val status : String,
    @SerializedName("category") val category : String?,
    @SerializedName("Writer_by_writer_id") val writerByWriterId : Writer,
    @SerializedName("book_id") val bookId : Int,
    @SerializedName("isNew") val isNew : Boolean?,
    @SerializedName("view_count") val viewCount : Int,
    @SerializedName("rate_sum") val rateSum : Double,
    @SerializedName("chapter_count") val chapterCount : Int
) : Parcelable

@Parcelize
data class Detail(
    @SerializedName("id") val bookId : Int,
    @SerializedName("cover_url") val coverUrl : String,
    @SerializedName("status") val status : String,
    @SerializedName("desc") val desc : String,
    @SerializedName("Writer_by_writer_id") val writerId : NewWriter,
    @SerializedName("genres") val genres : MutableList<Genres>,
    @SerializedName("Related_by_book") val relatedBook : MutableList<RelatedBook>
) : Parcelable


@Parcelize
data class Genres(
    @SerializedName("id") val id : Int,
    @SerializedName("title") val title : String
) : Parcelable

@Parcelize
data class RelatedBook(
    @SerializedName("id") val id : Int,
    @SerializedName("title") val title : String,
    @SerializedName("cover_url") val coverUrl : String
) : Parcelable