package com.grepy.msx.ngebacakuy.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.grepy.msx.ngebacakuy.utils.OnItemClicked
import com.grepy.msx.ngebacakuy.R
import com.grepy.msx.ngebacakuy.model.Book
import com.grepy.msx.ngebacakuy.model.Category
import com.grepy.msx.ngebacakuy.ui.category.CategoryActivity
import com.grepy.msx.ngebacakuy.ui.details.DetailsActivity
import com.grepy.msx.ngebacakuy.utils.OnCatClicked
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by lazy { ViewModelProvider(this).get(HomeViewModel::class.java) }
    private lateinit var adapter: UptodateBookAdapter
    private lateinit var categoryAdapter: CategoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setNewBookItem()
        setCategory()
    }

    private fun setCategory() {
        rv_category.layoutManager = GridLayoutManager(this.context, 2, GridLayoutManager.HORIZONTAL, false)
        categoryAdapter = CategoryAdapter()
        rv_category.adapter = categoryAdapter
        homeViewModel.getCategory().observe(this.viewLifecycleOwner, Observer {
            categoryAdapter.addCat(it)
        })

        categoryAdapter.setCatClicked(object : OnCatClicked{
            override fun itemClicked(category: Category) {
                setItemCategory(category)
            }

        })
    }

    private fun setNewBookItem() {
        rv_new_book.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        adapter = UptodateBookAdapter()
        rv_new_book.adapter = adapter
        homeViewModel.getDataUpToDte().observe(this.viewLifecycleOwner, Observer {
            adapter.addItems(it)
        })

        adapter.setOnItemClicked(object :
            OnItemClicked {
            override fun itemClicked(book: Book) {
                setItemBook(book)
            }
        })
    }

    private fun setItemBook(book: Book) {
        val intent = Intent(activity, DetailsActivity::class.java)
        intent.putExtra(DetailsActivity.NEW_BOOK, book)
        startActivity(intent)
    }

    private fun setItemCategory(category: Category) {
        val intent = Intent(activity, CategoryActivity::class.java)
        intent.putExtra(CategoryActivity.CATEGORY_ITEM, category)
        startActivity(intent)
    }

}