package com.example.dorixona.util

import android.content.Context
import com.example.dorixona.model.Books
import com.example.dorixona.model.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ShPHelper private constructor(context: Context) {
    val shared = context.getSharedPreferences("data", 0)
    val edit = shared.edit()


    companion object {
        private var instance: ShPHelper? = null
        fun getInstance(context: Context): ShPHelper {
            if (instance == null) {
                instance = ShPHelper(context)
            }
            return instance!!
        }
    }

    fun setBooks(book: Books) {
        val type = object : TypeToken<List<Books>>() {}.type
        val gson = Gson()

        val bookList: MutableList<Books>
        val str = shared.getString("Books", "")

        if (str == "") {
            bookList = mutableListOf()
        } else {
            bookList = gson.fromJson(str, type)
        }
        bookList.add(book)

        val edited = gson.toJson(bookList)
        edit.putString("Books", edited).apply()
    }

    fun removeBook(book: Books) {
        val type = object : TypeToken<List<Books>>() {}.type
        val gson = Gson()

        val bookList: MutableList<Books>
        val str = shared.getString("Books", "")

        if (str == "") {
            bookList = mutableListOf()
        } else {
            bookList = gson.fromJson(str, type)
        }
        for (i in bookList) {
            if (i.name == book.name && i.author == book.author) {
                bookList.remove(i)
            }
        }

        val edited = gson.toJson(bookList)
        edit.putString("Books", edited).apply()
    }

    fun getBooks(): MutableList<Books> {
        val type = object : TypeToken<List<Books>>() {}.type
        val gson = Gson()

        val bookList: MutableList<Books>
        val str = shared.getString("Books", "")

        if (str == "") {
            bookList = mutableListOf()
        } else {
            bookList = gson.fromJson(str, type)
        }
        return bookList
    }

    fun setUser(user: User) {
        val type = object : TypeToken<List<User>>() {}.type
        val gson = Gson()

        val userList: MutableList<User>
        val str = shared.getString("Users", "")

        if (str == "") {
            userList = mutableListOf()
        } else {
            userList = gson.fromJson(str, type)
        }
        userList.add(User(user.name, user.surname, user.email, user.password))

        val edited = gson.toJson(userList)
        edit.putString("Users", edited).apply()
    }

    fun updateUser(user: User, status: Boolean) {
        val type = object : TypeToken<List<User>>() {}.type
        val gson = Gson()

        val userList: MutableList<User>
        val str = shared.getString("Users", "")

        if (str == "") {
            userList = mutableListOf()
        } else {
            userList = gson.fromJson(str, type)
        }
        for (i in userList) {
            if (i.email == user.email && i.password == user.password) {
                userList.remove(i)
                user.status = status
                userList.add(user)
            }
        }

        val edited = gson.toJson(userList)
        edit.putString("Users", edited).apply()
    }

    fun getUser(): MutableList<User> {
        val type = object : TypeToken<List<User>>() {}.type
        val gson = Gson()

        val userList: MutableList<User>
        val str = shared.getString("Users", "")

        if (str == "") {
            userList = mutableListOf()
        } else {
            userList = gson.fromJson(str, type)
        }
        return userList
    }
}