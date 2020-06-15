package com.grepy.msx.ngebacakuy.utils

import com.grepy.msx.ngebacakuy.model.Book
import com.grepy.msx.ngebacakuy.model.Category

interface OnItemClicked {
    fun itemClicked(book : Book)
}

interface OnCatClicked {
    fun itemClicked(category: Category)
}