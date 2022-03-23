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
        fun saveClicked(doc: ApiResponse.Document?)
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

    val selectedItems = ObservableArrayList<Int>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchItemViewHolder =
        SearchItemViewHolder(ItemImageBinding.inflate(LayoutInflater.from(parent.context)))

    override fun onBindViewHolder(holderItem: SearchItemViewHolder, position: Int) {
        holderItem.bindTo(this, getItem(position))
    }

    fun addItemToSelection(position: Int): Boolean = selectedItems.add(position)

    fun isItemSelected(position: Int) = selectedItems.contains(position)

    fun isLastSelectedItem(position: Int) =
        isItemSelected(position) && selectedItems.size == 1

    fun deleteAllItems() = selectedItems.clear()

}