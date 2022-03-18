package com.example.imagesearchbox.http.model

import androidx.annotation.Keep

@Keep
data class Image(
    val documents: List<Document>,
    val meta: Meta
) {
    @Keep
    data class Document(
        val collection: String,
        val datetime: String,
        val display_sitename: String,
        val doc_url: String,
        val height: Int,
        val image_url: String,
        val thumbnail_url: String,
        val width: Int
    )
}