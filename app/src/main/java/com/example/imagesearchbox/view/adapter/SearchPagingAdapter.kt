package com.example.imagesearchbox.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.imagesearchbox.databinding.ItemImageBinding
import com.example.imagesearchbox.model.ApiResponse

class SearchPagingAdapter(private val clickListener: ClickInterface) :
    PagingDataAdapter<ApiResponse.Document, SearchPagingAdapter.SearchPagingViewHolder>(
        ItemDiffCallBack
    ) {

    interface ClickInterface {
        fun saveClicked(view: ImageView, doc: ApiResponse.Document?)
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
        fun bind(doc: ApiResponse.Document?) {
            binding.item = doc
            binding.imgHeart.setOnClickListener {
                clickListener.saveClicked(binding.imgHeart, doc)
            }
        }
    }

    companion object {
        private val ItemDiffCallBack = object : DiffUtil.ItemCallback<ApiResponse.Document>() {
            override fun areItemsTheSame(
                oldItem: ApiResponse.Document,
                newItem: ApiResponse.Document
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: ApiResponse.Document,
                newItem: ApiResponse.Document
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}
