package com.example.imagesearchbox.http.model

import androidx.annotation.Keep

@Keep
data class Video(
    val documents: List<Document>,
    val meta: Meta
) {
    @Keep
    data class Document(
        val author: String,
        val datetime: String,
        val play_time: Int,
        val thumbnail: String,
        val title: String,
        val url: String
    )
}