package com.example.imagesearchbox.ui.search.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.imagesearchbox.databinding.ItemImageBinding
import com.example.imagesearchbox.http.model.Response

class SearchPagingAdapter(private val clickListener: ClickInterface) : PagingDataAdapter<Response.Document, SearchPagingAdapter.SearchPagingViewHolder>(ItemDiffCallBack()) {

    interface ClickInterface {
        fun saveClicked(view: ImageView, doc: Response.Document?)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchPagingViewHolder {
        val binding = ItemImageBinding.inflate(LayoutInflater.from(parent.context))
        return SearchPagingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchPagingViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class SearchPagingViewHolder(val binding: ItemImageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(doc: Response.Document?) {
            binding.item = doc
            binding.imgHeart.setOnClickListener {
                clickListener.saveClicked(binding.imgHeart, doc)
            }
        }
    }
}
