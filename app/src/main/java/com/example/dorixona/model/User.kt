package com.example.dorixona.model

import java.io.Serializable

data class User(
    var name: String,
    var surname: String,
    var email: String,
    var password: String,
    var status: Boolean = false
): Serializable
