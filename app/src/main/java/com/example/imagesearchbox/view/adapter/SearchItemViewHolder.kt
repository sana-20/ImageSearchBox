package com.example.imagesearchbox.view.adapter

import androidx.databinding.ObservableList
import androidx.recyclerview.widget.RecyclerView
import com.example.imagesearchbox.databinding.ItemImageBinding
import com.example.imagesearchbox.model.ApiResponse

class SearchItemViewHolder(private val binding: ItemImageBinding) :
    RecyclerView.ViewHolder(binding.root) {

    private lateinit var adapter: SearchAdapter

    fun bindTo(adapter: SearchAdapter, doc: ApiResponse.Document?) {
        this.adapter = adapter
        binding.item = doc

        binding.imgFavourite.setOnClickListener {
            if (!adapter.isFavouriteItem(layoutPosition)) {
                adapter.clickInterface.favouriteClicked(doc)

                if (adapter.isLastItem(layoutPosition)) {
                    return@setOnClickListener
                }
                addItem()
            }
        }

        adapter.favouriteItems.addOnListChangedCallback(onFavouriteItemsChanged)

        listChanged()
    }

    private val onFavouriteItemsChanged =
        object : ObservableList.OnListChangedCallback<ObservableList<Int>>() {

            override fun onChanged(sender: ObservableList<Int>?) {
                listChanged()
            }

            override fun onItemRangeChanged(
                sender: ObservableList<Int>?,
                positionStart: Int,
                itemCount: Int
            ) {
                listChanged()
            }

            override fun onItemRangeInserted(
                sender: ObservableList<Int>?,
                positionStart: Int,
                itemCount: Int
            ) {
                listChanged()
            }

            override fun onItemRangeMoved(
                sender: ObservableList<Int>?,
                fromPosition: Int,
                toPosition: Int,
                itemCount: Int
            ) {
                listChanged()
            }

            override fun onItemRangeRemoved(
                sender: ObservableList<Int>?,
                positionStart: Int,
                itemCount: Int
            ) {
                listChanged()
            }

        }

    private fun listChanged() {
        binding.imgFavourite.isSelected = adapter.isFavouriteItem(layoutPosition)
    }

    private fun addItem() {
        layoutPosition.let {
            adapter.addItemToFavourite(it)
        }
    }
}
