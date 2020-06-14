package com.grepy.msx.ngebacakuy.ui.details

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.grepy.msx.ngebacakuy.R
import com.grepy.msx.ngebacakuy.constant.Constant
import com.grepy.msx.ngebacakuy.model.Book
import com.grepy.msx.ngebacakuy.model.Detail
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {

    companion object {
        const val NEW_BOOK = "new_book"
    }

    private lateinit var detailsViewModel: DetailsViewModel
    private lateinit var relatedAdapter: RelatedAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        setSupportActionBar(toolbar_details)
        collapsing_toolbar.setStatusBarScrimColor(Color.WHITE)
        detailsViewModel = ViewModelProvider(this).get(DetailsViewModel::class.java)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        prepareData()
    }

    private fun prepareData() {
        val newBook = intent.getParcelableExtra<Book>(NEW_BOOK)
        when{
            newBook is Book -> {
                supportActionBar?.title = newBook.titleBook
                detailsViewModel.getDetailData(newBook.id).observe(this, Observer {
                    for (i in it.indices) {
                        setDataByData(it[i])
                    }
                })
            }
        }
    }

    private fun setDataByData(detail: Detail) {
        var url  = "https://cabaca.id:8443/api/v2/files/${detail.coverUrl}&api_key=${Constant.HEADERS}"
        Glide.with(this).load(url).into(details_thumbnail)
        desc_detail.text = detail.desc
        penulis_name.text = detail.writerId.userByUser.name ?: "-"
        relatedAdapter = RelatedAdapter()
        rv_related_book.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rv_related_book.adapter = relatedAdapter
        relatedAdapter.addBook(detail.relatedBook)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}