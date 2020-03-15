package com.homework.thoughtworkstask.recycler

import android.content.Context
import androidx.recyclerview.widget.RecyclerView


abstract class RecyclerViewAdapter<T>(activity: Context, loadMoreInterface: (()->Unit))
    : BaseRecyclerViewAdapter<T>(activity, loadMoreInterface) {

    override fun onNewBindViewHolder(holder: RecyclerView.ViewHolder, position: Int, payloads: List<*>) {
        onNewBindViewHolder(holder, position)
    }

    /**
     * @param holder
     * @param position
     */
    abstract fun onNewBindViewHolder(holder: RecyclerView.ViewHolder, position: Int)
}
