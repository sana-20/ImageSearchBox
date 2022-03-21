package com.example.imagesearchbox.ui.box

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.imagesearchbox.databinding.ItemBoxBinding
import com.example.imagesearchbox.db.MyBox
import kotlin.properties.Delegates

class MyBoxRecyclerAdapter: RecyclerView.Adapter<MyBoxRecyclerAdapter.ViewHolder>() {

    private var items: List<MyBox> by Delegates.observable(arrayListOf()) { _, _, _ ->
        notifyDataSetChanged()
    }

    fun updateData(newData: List<MyBox>) {
        items = newData
    }

    inner class ViewHolder(private val binding: ItemBoxBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MyBox){
            binding.item = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemBoxBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}