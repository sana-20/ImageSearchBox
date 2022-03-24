package com.example.imagesearchbox.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ObservableArrayList
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.imagesearchbox.databinding.ItemImageBinding
import com.example.imagesearchbox.model.ApiResponse

class SearchAdapter(
    val clickInterface: ClickInterface
) : PagingDataAdapter<ApiResponse.Document, SearchItemViewHolder>(ItemDiffCallBack) {

    interface ClickInterface {
        fun favouriteClicked(doc: ApiResponse.Document?)
    }

    companion object {
        val ItemDiffCallBack = object : DiffUtil.ItemCallback<ApiResponse.Document>() {
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

    val favouriteItems = ObservableArrayList<Int>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchItemViewHolder =
        SearchItemViewHolder(ItemImageBinding.inflate(LayoutInflater.from(parent.context)))

    override fun onBindViewHolder(holderItem: SearchItemViewHolder, position: Int) {
        holderItem.bindTo(this, getItem(position))
    }

    fun addItemToFavourite(position: Int): Boolean = favouriteItems.add(position)

    fun isFavouriteItem(position: Int) = favouriteItems.contains(position)

    fun isLastItem(position: Int) =
        isFavouriteItem(position) && favouriteItems.size == 1

    fun deleteAllFavouriteItems() = favouriteItems.clear()

}