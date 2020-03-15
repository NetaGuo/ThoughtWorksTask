package com.homework.thoughtworkstask.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.homework.thoughtworkstask.R
import com.homework.thoughtworkstask.entity.TwitterItem
import com.homework.thoughtworkstask.recycler.RecyclerViewAdapter
import com.homework.thoughtworkstask.viewholder.TwitterViewHolder

class TwitterAdapter(context: Context, loadMore: (() -> Unit)) :
    RecyclerViewAdapter<TwitterItem>(context, loadMore) {


    override fun onNewBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? TwitterViewHolder)?.fillData(data!![position])
    }

    override val newItemCount: Int
        get() = data!!.size

    override fun onNewCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return TwitterViewHolder(inflater.inflate(R.layout.layout_twitter_item, parent, false))
    }

    override fun getNewItemViewType(position: Int): Int {
        return 0
    }
}