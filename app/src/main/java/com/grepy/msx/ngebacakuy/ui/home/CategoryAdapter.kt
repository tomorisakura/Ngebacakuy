package com.grepy.msx.ngebacakuy.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.grepy.msx.ngebacakuy.R
import com.grepy.msx.ngebacakuy.model.Category
import com.grepy.msx.ngebacakuy.utils.OnCatClicked
import kotlinx.android.synthetic.main.category_list.view.*

class CategoryAdapter : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    private var category: MutableList<Category> = mutableListOf()
    private var onCatClicked : OnCatClicked? = null

    internal fun setCatClicked(onItem: OnCatClicked) {
        this.onCatClicked = onItem
    }

    inner class CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(category: Category) {
            with(itemView) {
                cat_name.text = category.title
                this.setOnClickListener{ onCatClicked?.itemClicked(category) }
            }
        }
    }

    internal fun addCat(c : List<Category>) {
        notifyDataSetChanged()
        category.addAll(c)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.category_list, parent, false)
        return CategoryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return category.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(category[position])
    }

}