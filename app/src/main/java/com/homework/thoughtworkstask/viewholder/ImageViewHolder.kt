package com.homework.thoughtworkstask.viewholder

import android.view.View
import androidx.constraintlayout.solver.widgets.ConstraintTableLayout
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.homework.thoughtworkstask.R
import com.homework.thoughtworkstask.entity.ImageItem
import com.homework.thoughtworkstask.recycler.BaseViewHolder
import com.homework.thoughtworkstask.utils.ScreenUtils
import kotlinx.android.synthetic.main.layout_image_item.view.*

class ImageViewHolder(container: View) : BaseViewHolder(container) {


    fun fillData(itemData: ImageItem, size: Int) {
        with(itemView) {
            val lp = ivImageItem.layoutParams as ConstraintLayout.LayoutParams
            when(size){
                1->{
                    lp.height = ScreenUtils.dip2px(mContext,200f)
                    lp.width = ScreenUtils.dip2px(mContext,200f)
                }
                else->{
                    lp.height = ScreenUtils.dip2px(mContext,80f)
                    lp.width = ScreenUtils.dip2px(mContext,80f)
                }
            }
            ivImageItem.layoutParams = lp
            Glide.with(mContext).load(itemData.url).placeholder(R.drawable.ic_image)
                .error(R.drawable.ic_image).into(ivImageItem)
        }
    }


}