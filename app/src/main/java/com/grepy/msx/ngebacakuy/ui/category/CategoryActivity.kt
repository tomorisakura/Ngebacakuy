package com.grepy.msx.ngebacakuy.ui.category

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.grepy.msx.ngebacakuy.R
import com.grepy.msx.ngebacakuy.model.Category
import kotlinx.android.synthetic.main.activity_category.*

class CategoryActivity : AppCompatActivity() {

    companion object {
        const val CATEGORY_ITEM = "category"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)
        setSupportActionBar(toolbar_category)
        prepareData()
    }

    private fun prepareData() {
        val item = intent.getParcelableExtra<Category>(CATEGORY_ITEM)
        supportActionBar?.title = item.title
    }
}