package com.homework.thoughtworkstask.recycler

import android.app.Activity
import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView


open class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    protected var mContext: Context = itemView.context


    protected val activity: Activity
        get() = mContext as Activity


    @Suppress("UNCHECKED_CAST")
    protected fun <T : View> getView(viewId: Int): T {
        return itemView.findViewById<View>(viewId) as T
    }


}
