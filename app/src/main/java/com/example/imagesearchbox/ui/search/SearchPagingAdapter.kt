package com.example.imagesearchbox.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.imagesearchbox.databinding.ItemImageBinding
import com.example.imagesearchbox.http.model.Response

class SearchPagingAdapter : PagingDataAdapter<Response.Document, SearchPagingViewHolder>(ItemDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchPagingViewHolder {
        val binding = ItemImageBinding.inflate(LayoutInflater.from(parent.context))
        return SearchPagingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchPagingViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class SearchPagingViewHolder(val binding: ItemImageBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(doc: Response.Document?) {
        binding.item = doc
    }
}


class ItemDiffCallBack : DiffUtil.ItemCallback<Response.Document>() {
    override fun areItemsTheSame(oldItem: Response.Document, newItem: Response.Document): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Response.Document, newItem: Response.Document): Boolean {
        return oldItem == newItem
    }
}
