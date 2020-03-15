package com.homework.thoughtworkstask.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.homework.thoughtworkstask.R
import com.homework.thoughtworkstask.entity.ImageItem
import com.homework.thoughtworkstask.recycler.BaseViewAdapter
import com.homework.thoughtworkstask.viewholder.ImageViewHolder

class ImageAdapter (context: Context): BaseViewAdapter<ImageItem>(context){


    override val newItemCount: Int
        get() = data!!.size

    override fun onNewCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ImageViewHolder(inflater.inflate(R.layout.layout_image_item, parent, false))
    }

    override fun onNewBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? ImageViewHolder)?.fillData(data!![position],data!!.size)
    }

    override fun getNewItemViewType(position: Int): Int {
        return 0
    }
}