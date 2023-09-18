package com.example.dorixona.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dorixona.databinding.BookItemBinding
import com.example.dorixona.databinding.LanguageItemBinding
import com.example.dorixona.model.Books
import com.example.dorixona.model.Language

class LanguageAdapter(
    var languageList: MutableList<Language>,
    var myBook: MyBook,
    var context: Context
) : RecyclerView.Adapter<LanguageAdapter.MyHolder>() {

    class MyHolder(binding: LanguageItemBinding) : RecyclerView.ViewHolder(binding.root) {
        var name = binding.textView14
        var img = binding.flag
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(
            LanguageItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        var book = languageList[position]
        holder.name.text = book.name
        holder.img.setImageResource(book.img)
        holder.itemView.setOnClickListener {
            myBook.onItemClick(book)
        }

    }

    override fun getItemCount(): Int {
        return languageList.size
    }

    interface MyBook {
        fun onItemClick(book: Language)
    }
}