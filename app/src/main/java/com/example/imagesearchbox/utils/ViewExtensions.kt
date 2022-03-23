package com.example.imagesearchbox.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.imagesearchbox.utils.Utils.setDateFormat
import java.text.SimpleDateFormat

@BindingAdapter("loadUrl")
fun ImageView.loadUrl(url: String) {
    Glide.with(this)
        .load(url)
        .into(this)
}

@BindingAdapter("setDate")
fun TextView.setDate(datetime: String) {
    val formatDate = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").parse(datetime)
    text = setDateFormat(formatDate)
}