package com.example.imagesearchbox.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.imagesearchbox.databinding.ItemBoxBinding
import com.example.imagesearchbox.model.MyBox

class MyBoxRecyclerAdapter(private val onClicked: (id: Int) -> Unit): ListAdapter<MyBox, MyBoxRecyclerAdapter.ViewHolder>(MyBoxDiffUtil) {

    inner class ViewHolder(private val binding: ItemBoxBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MyBox){
            binding.item = item
            binding.imgFavourite.setOnClickListener {
                onClicked.invoke(item.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemBoxBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    object MyBoxDiffUtil: DiffUtil.ItemCallback<MyBox>() {
        override fun areItemsTheSame(oldItem: MyBox, newItem: MyBox): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MyBox, newItem: MyBox): Boolean {
            return oldItem == newItem
        }
    }
}
