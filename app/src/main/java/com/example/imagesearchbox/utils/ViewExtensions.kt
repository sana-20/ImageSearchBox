package com.example.imagesearchbox.utils

import android.app.Activity
import android.content.Context.INPUT_METHOD_SERVICE
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
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
    val newText = SimpleDateFormat("YYYY/MM/DD HH:mm").format(formatDate)
    text = newText
}


fun Activity.hideSoftKeyboard() {
    (getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager).apply {
        hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }
}