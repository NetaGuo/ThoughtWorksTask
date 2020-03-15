package com.homework.thoughtworkstask.viewholder

import android.view.View
import com.bumptech.glide.Glide
import com.homework.thoughtworkstask.R
import com.homework.thoughtworkstask.entity.CommentItem
import com.homework.thoughtworkstask.recycler.BaseViewHolder
import kotlinx.android.synthetic.main.layout_comment_item.view.*

class CommentViewHolder(container: View) : BaseViewHolder(container) {

    fun fillData(itemData: CommentItem) {
        with(itemView) {
            Glide.with(mContext).load(itemData.sender.avatar).error(R.drawable.ic_avatar)
                .placeholder(R.drawable.ic_avatar).into(ivCommentAvatar)
            tvComment.text = itemData.content
            tvNickName.text = itemData.sender.nick
        }
    }


}