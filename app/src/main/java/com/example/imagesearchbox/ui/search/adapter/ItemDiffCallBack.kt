package com.example.imagesearchbox.ui.search.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.imagesearchbox.http.model.Response

class ItemDiffCallBack : DiffUtil.ItemCallback<Response.Document>() {
    override fun areItemsTheSame(oldItem: Response.Document, newItem: Response.Document): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Response.Document, newItem: Response.Document): Boolean {
        return oldItem == newItem
    }
}