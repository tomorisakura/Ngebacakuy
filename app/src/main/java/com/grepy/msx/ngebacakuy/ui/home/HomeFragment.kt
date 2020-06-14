package com.grepy.msx.ngebacakuy.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.grepy.msx.ngebacakuy.OnItemClicked
import com.grepy.msx.ngebacakuy.R
import com.grepy.msx.ngebacakuy.model.Book
import com.grepy.msx.ngebacakuy.ui.details.DetailsActivity
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var adapter: UptodateBookAdapter

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
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        setNewBookItem()
    }

    private fun setNewBookItem() {
        rv_new_book.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        adapter = UptodateBookAdapter()
        rv_new_book.adapter = adapter
        homeViewModel.getDataUpToDte().observe(this.viewLifecycleOwner, Observer {
            adapter.addItems(it)
        })

        adapter.setOnItemClicked(object : OnItemClicked{
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
}