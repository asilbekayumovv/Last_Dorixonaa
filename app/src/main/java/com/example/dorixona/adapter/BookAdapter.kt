package com.example.dorixona.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dorixona.databinding.BookItemBinding
import com.example.dorixona.model.Books

class BookAdapter(
    var bookList: MutableList<Books>,
    var myBook: MyBook,
    var context: Context
) : RecyclerView.Adapter<BookAdapter.MyHolder>() {

    class MyHolder(binding: BookItemBinding) : RecyclerView.ViewHolder(binding.root) {
        var name = binding.name
        var img = binding.image
        var author = binding.author
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(
            BookItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        var book = bookList[position]
        holder.name.text = book.name
        holder.img.setImageResource(book.img)
        holder.author.text = book.author
        holder.itemView.setOnClickListener {
            myBook.onItemClick(book)
        }

    }

    override fun getItemCount(): Int {
        return bookList.size
    }

    interface MyBook {
        fun onItemClick(book: Books)
    }
}