package com.example.imagesearchbox.http.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Response(
    val documents: List<Document>,
    val meta: Meta
) {
    @Keep
    data class Document(
        val datetime: String,
        @SerializedName("thumbnail", alternate = ["thumbnail_url"])
        val thumbnail: String,
    )

    @Keep
    data class Meta(
        val is_end: Boolean,
        val pageable_count: Int,
        val total_count: Int
    )
}