package com.homework.thoughtworkstask.recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import java.util.*


abstract class BaseViewAdapter<T>(protected var mActivity: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var data: MutableList<T>? = null
    protected var inflater: LayoutInflater

    /**
     * 要展示的item个数
     *
     * @return
     */
    abstract val newItemCount: Int

    fun getDataT(p: Int): T {
        return data!![p]
    }

    fun setDataT(p: Int, t: T) {
        data!![p] = t
        notifyItemChanged(p)
    }

    init {
        this.data = ArrayList()
        inflater = LayoutInflater.from(mActivity)
    }

    override fun getItemCount(): Int {
        return newItemCount
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return onNewCreateViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        onNewBindViewHolder(holder, position)
    }

    override fun getItemViewType(position: Int): Int {
        return getNewItemViewType(position)
    }


    /**
     * 要显示的item
     *
     * @param parent
     * @param viewType 对应的viewType
     * @return
     */
    abstract fun onNewCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder

    /**
     * 绑定item
     *
     * @param holder   对应viewType的holder
     * @param position list的下标
     */
    abstract fun onNewBindViewHolder(holder: RecyclerView.ViewHolder, position: Int)

    /**
     * 在需要foot时要展示的itemtype 不可返回9999
     *
     * @param position
     * @return
     */
    abstract fun getNewItemViewType(position: Int): Int


    fun clearNotNotify() {
        if (data != null) {
            data!!.clear()
        }
    }

    fun clear() {
        if (data != null) {
            data!!.clear()
            notifyDataSetChanged()
        }
    }

    fun addData(datas: List<T>?) {
        if (datas != null) {
            data!!.addAll(datas)
            notifyDataSetChanged()
        }
    }

    fun setTrueData(data: MutableList<T>?) {
        if (data != null) {
            this.data = data
        } else {
            this.data!!.clear()
        }
        notifyDataSetChanged()
    }


}
