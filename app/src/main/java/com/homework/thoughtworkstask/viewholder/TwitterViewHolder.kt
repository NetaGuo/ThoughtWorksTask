package com.homework.thoughtworkstask.viewholder

import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.homework.thoughtworkstask.R
import com.homework.thoughtworkstask.adapter.CommentAdapter
import com.homework.thoughtworkstask.adapter.ImageAdapter
import com.homework.thoughtworkstask.base.gone
import com.homework.thoughtworkstask.base.visible
import com.homework.thoughtworkstask.entity.TwitterItem
import com.homework.thoughtworkstask.recycler.BaseViewHolder
import com.homework.thoughtworkstask.recycler.GridSpaceItemDecoration
import kotlinx.android.synthetic.main.layout_twitter_item.view.*

class TwitterViewHolder(container: View) : BaseViewHolder(container) {

    fun fillData(itemData: TwitterItem) {

        with(itemView) {
            Log.e("itemData===","${itemData.sender.username}")
            //nickname
            tvName.text = itemData.sender.username
            //content
            if (!itemData.content.isNullOrBlank()) {
                tvContent.visible()
                tvContent.text = itemData.content
            }else {
                tvContent.gone()
            }
            //avatar
            Glide.with(mContext).load(itemData.sender.avatar).placeholder(R.drawable.ic_avatar)
                .error(R.drawable.ic_avatar).into(ivAvatar)
            //images
            if (!itemData.images.isNullOrEmpty()) {
                grvImages.visible()
                grvImages.columns = if (itemData.images.size == 1) 1 else 3
                grvImages.addItemDecoration(GridSpaceItemDecoration())
                val adapterI = ImageAdapter(mContext)
                grvImages.init(adapterI)
                adapterI.setTrueData(itemData.images)
            } else {
                grvImages.gone()
            }
            //comments
            if (!itemData.comments.isNullOrEmpty()) {
                lrvComment.visible()
                val adapterC = CommentAdapter(mContext)
                lrvComment.init(adapterC)
                adapterC.setTrueData(itemData.comments)
            } else {
                lrvComment.gone()
            }

        }

    }

}