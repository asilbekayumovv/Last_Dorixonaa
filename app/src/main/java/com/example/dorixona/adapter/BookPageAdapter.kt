package com.example.dorixona.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dorixona.databinding.ViewPagerBookBinding
import com.example.dorixona.model.book_pager

class BookPageAdapter(
    var bookList: MutableList<book_pager>,
    var context: Context
) : RecyclerView.Adapter<BookPageAdapter.MyHolder>() {

    class MyHolder(binding: ViewPagerBookBinding) : RecyclerView.ViewHolder(binding.root) {
        var name = binding.textView7
        var img = binding.imageView5
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(
            ViewPagerBookBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        var book = bookList[position % bookList.size]
        holder.name.text = book.txt
        holder.img.setImageResource(book.img)
    }

    override fun getItemCount(): Int {
        return bookList.size * 2
    }
}