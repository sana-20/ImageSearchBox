package com.example.imagesearchbox.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide


@BindingAdapter("loadUrl")
fun ImageView.loadUrl(url: String?) {
    url?.let {
        Glide.with(this)
            .load(it)
//            .placeholder(R.drawable.default_png_120px_3)
//            .error(R.drawable.default_png_120px_3)
            .into(this)
    }
}