package com.example.dorixona.model

import java.io.Serializable

data class Books(
    var name: String,
    var author: String,
    var img: Int,
    var all: Boolean = true,
    var roman: Boolean = false,
    var darslik: Boolean = false,
    var qissa: Boolean = false,
    var saved: Boolean = false
): Serializable
