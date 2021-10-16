package com.stockbit.hiring.model

data class Response(
    val Data: ArrayList<Data>,
    val HasWarning: Boolean,
    val Message: String,
    val Type: Int
)