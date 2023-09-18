package com.example.dorixona.model

import java.io.Serializable

data class Filter(
    var name: String,
    var state: Boolean = false
) : Serializable
