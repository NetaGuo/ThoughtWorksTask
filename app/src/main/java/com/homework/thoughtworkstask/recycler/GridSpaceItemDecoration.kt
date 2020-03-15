package com.homework.thoughtworkstask.recycler

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class GridSpaceItemDecoration(val space: Int = 10, private val columnNum: Int = 3) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val viewPosition = parent.getChildAdapterPosition(view)
        val remainder = viewPosition % columnNum
        when {
            remainder == 0 -> {
                //第一列左边无边距
                outRect.left = 0
            }
            remainder < this.columnNum -> {
                //第一列之外的左边距都有
                outRect.left = space
            }
        }
        if (viewPosition >= columnNum) {
            outRect.top = space
        } else {
            outRect.top = 0
        }


    }

}