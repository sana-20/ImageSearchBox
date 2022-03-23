package com.example.imagesearchbox.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.imagesearchbox.databinding.ItemBoxBinding
import com.example.imagesearchbox.model.MyBox
import kotlin.properties.Delegates

class MyBoxRecyclerAdapter(val clickInterface: ClickInterface): RecyclerView.Adapter<MyBoxRecyclerAdapter.ViewHolder>() {

    interface ClickInterface {
        fun saveClicked(id: Int)
    }

    private var items: List<MyBox> by Delegates.observable(arrayListOf()) { _, _, _ ->
        notifyDataSetChanged()
    }

    fun updateData(newData: List<MyBox>) {
        items = newData
    }

    inner class ViewHolder(private val binding: ItemBoxBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MyBox){
            binding.item = item

            binding.imgHeart.setOnClickListener {
                clickInterface.saveClicked(item.id)
            }
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