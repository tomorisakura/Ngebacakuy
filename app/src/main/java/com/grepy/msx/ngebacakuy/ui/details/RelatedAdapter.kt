package com.grepy.msx.ngebacakuy.ui.details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.grepy.msx.ngebacakuy.R
import com.grepy.msx.ngebacakuy.constant.Constant
import com.grepy.msx.ngebacakuy.model.RelatedBook
import kotlinx.android.synthetic.main.list_related.view.*

class RelatedAdapter : RecyclerView.Adapter<RelatedAdapter.UserViewHolder>() {

    private var user : MutableList<RelatedBook> = mutableListOf()

    internal fun addBook(u : List<RelatedBook>) {
        notifyDataSetChanged()
        user.clear()
        user.addAll(u)
    }

    inner class UserViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        fun bind(book: RelatedBook) {
            with(itemView) {
                val url = "https://cabaca.id:8443/api/v2/files/${book.coverUrl}&api_key=${Constant.HEADERS}"
                Glide.with(this.context).load(url).into(thumb_related)
                tv_name.text = book.title
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_related, parent, false)
        return UserViewHolder(view)
    }

    override fun getItemCount(): Int {
        return user.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(user[position])
    }
}