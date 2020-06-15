package com.grepy.msx.ngebacakuy.repository.response

import com.google.gson.annotations.SerializedName
import com.grepy.msx.ngebacakuy.model.Category


data class CategoryResponse(
    @SerializedName("resource") val result : MutableList<Category>
)