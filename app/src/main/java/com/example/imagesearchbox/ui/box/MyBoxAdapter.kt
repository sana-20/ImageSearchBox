package com.example.imagesearchbox.ui.box

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.imagesearchbox.R
import com.example.imagesearchbox.db.MyBox

class MyBoxAdapter : ListAdapter<MyBox, MyBoxAdapter.ViewHolder>(WORDS_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.url)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val image: ImageView = itemView.findViewById(R.id.img_thumbnail)

        fun bind(text: String?) {
            Glide.with(itemView.context)
                .load(text)
                .into(image)
        }

        companion object {
            fun create(parent: ViewGroup): ViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_box, parent, false)
                return ViewHolder(view)
            }
        }
    }

    companion object {
        private val WORDS_COMPARATOR = object : DiffUtil.ItemCallback<MyBox>() {
            override fun areItemsTheSame(oldItem: MyBox, newItem: MyBox): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: MyBox, newItem: MyBox): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}
