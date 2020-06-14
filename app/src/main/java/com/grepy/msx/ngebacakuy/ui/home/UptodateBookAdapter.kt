package com.grepy.msx.ngebacakuy.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.grepy.msx.ngebacakuy.OnItemClicked
import com.grepy.msx.ngebacakuy.R
import com.grepy.msx.ngebacakuy.constant.Constant
import com.grepy.msx.ngebacakuy.model.Book
import kotlinx.android.synthetic.main.list_book_uptodate.view.*

class UptodateBookAdapter : RecyclerView.Adapter<UptodateBookAdapter.UptodateBookViewHolder>() {

    private val book : MutableList<Book> = mutableListOf()
    private var onItemCliced : OnItemClicked? = null

    internal fun addItems(b : MutableList<Book>) {
        notifyDataSetChanged()
        book.clear()
        book.addAll(b)
    }

    fun setOnItemClicked(onItemClicked: OnItemClicked) {
        this.onItemCliced = onItemClicked
    }

    inner class UptodateBookViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        fun bind(book : Book) {
            with(itemView) {
                val url = "https://cabaca.id:8443/api/v2/files/${book.coverUrl}&api_key=${Constant.HEADERS}"
                Glide.with(this.context).load(url).into(thumb_book)
                book_name_list.text = book.titleBook
                itemView.setOnClickListener { onItemCliced?.itemClicked(book) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UptodateBookViewHolder {
        val mView = LayoutInflater.from(parent.context).inflate(R.layout.list_book_uptodate, parent, false)
        return UptodateBookViewHolder(mView)
    }

    override fun getItemCount(): Int {
        return book.size
    }

    override fun onBindViewHolder(holder: UptodateBookViewHolder, position: Int) {
        holder.bind(book[position])
    }
}