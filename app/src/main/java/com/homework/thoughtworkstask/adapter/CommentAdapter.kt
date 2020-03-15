package com.homework.thoughtworkstask.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.homework.thoughtworkstask.R
import com.homework.thoughtworkstask.entity.CommentItem
import com.homework.thoughtworkstask.recycler.BaseViewAdapter
import com.homework.thoughtworkstask.viewholder.CommentViewHolder

class CommentAdapter(context: Context) : BaseViewAdapter<CommentItem>(context) {


    override val newItemCount: Int
        get() = data!!.size

    override fun onNewCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CommentViewHolder(inflater.inflate(R.layout.layout_comment_item, parent, false))
    }

    override fun onNewBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? CommentViewHolder)?.fillData(data!![position])
    }

    override fun getNewItemViewType(position: Int): Int {
        return 0
    }
}