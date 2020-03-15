package com.homework.thoughtworkstask.recycler

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IntDef
import androidx.recyclerview.widget.RecyclerView
import com.homework.thoughtworkstask.R
import java.util.*


abstract class BaseRecyclerViewAdapter<T>(protected var mActivity: Context, private var mLMIF: (()->Unit)) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var data: MutableList<T>? = null
    protected var inflater: LayoutInflater
    private var isEnabledFoot = true
    private var showNoDataFoot = true
    private var notShowNoDataFoot = false
    var noMoreText = ""

    @FootType
    protected var type = ListFooterType.FOOT_LOADING_ADD
    private var footerText: String = ""

    abstract val newItemCount: Int


    @IntDef(ListFooterType.FOOT_LOADING_ADD, ListFooterType.FOOT_ERROR_LOADDATA,
            ListFooterType.FOOT_LOADING_MORE, ListFooterType.FOOT_NO_LOADDATA)
    @Retention(AnnotationRetention.SOURCE)
    annotation class FootType

    init {
        this.data = ArrayList()
        this.inflater = LayoutInflater.from(mActivity)
    }

    fun setEnabledFoot(enabledFoot: Boolean) {
        if (enabledFoot != isEnabledFoot) {
            isEnabledFoot = enabledFoot
            notifyDataSetChanged()
        }

    }

    fun makeTypeNull(){
        this.type = 0
    }

    fun setTrueData(tList: List<T>?) {
        if (null != tList) {
            data!!.clear()
            data!!.addAll(tList)
        } else {
            data!!.clear()
        }
        notifyDataSetChanged()
    }

    fun addData(tList: List<T>?) {
        if (null != tList) {
            data!!.addAll(tList)
            notifyDataSetChanged()
        }

    }

    fun addDaTa(obj: T?) {
        if (null != obj) {
            data!!.add(obj)
        }
    }

    fun addData(position: Int, obj: T?) {
        if (null != obj) {
            data!!.add(position, obj)
            notifyItemInserted(position)
        }
    }

    fun clear() {
        if (null != data) {
            data!!.clear()
            notifyDataSetChanged()
        }
    }

    fun clear(isNotify: Boolean) {
        if (null != data) {
            data!!.clear()
            if (isNotify) notifyDataSetChanged()
        }
    }

    fun delete(position: Int) {
        if (position < data!!.size) {
            data!!.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    @SuppressLint("SwitchIntDef")
    fun setFootType(@FootType type: Int) {
        if (!isEnabledFoot) {
            return
        }
        if (this.type == ListFooterType.FOOT_NO_LOADDATA && type == ListFooterType.FOOT_LOADING_MORE) {//如果是没有更多数据就不能显示正在加载了
            return
        }
        if (notShowNoDataFoot) {
            setShowNoDataFoot(true)
        }
        this.type = type
        when (type) {
            ListFooterType.FOOT_LOADING_ADD -> this.footerText = mActivity.resources.getString(R.string.add_loading)
            ListFooterType.FOOT_LOADING_MORE -> this.footerText = mActivity.resources.getString(R.string.loading_more)
            ListFooterType.FOOT_NO_LOADDATA -> {
                if (noMoreText.isNotEmpty()){
                    this.footerText = noMoreText
                }else{
                    this.footerText = mActivity.resources.getString(R.string.no_loading_data)
                }
                if (notShowNoDataFoot) {
                    setShowNoDataFoot(false)
                }
            }
            ListFooterType.FOOT_ERROR_LOADDATA -> this.footerText = mActivity.resources.getString(R.string.error_loading_data)
        }
        notifyItemChanged(itemCount - 1)
    }


    private fun setShowNoDataFoot(enabledFooter: Boolean) {
        if (enabledFooter != showNoDataFoot) {
            this.showNoDataFoot = enabledFooter
            if (!showNoDataFoot) {
                notShowNoDataFoot = true
            }
        }
    }

    override fun getItemCount(): Int {
        return newItemCount + if (isEnabledFoot) 1 else 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val vh: RecyclerView.ViewHolder
        if (viewType == 9999) {
            vh = FooterViewHolder(parent)
        } else {
            vh = onNewCreateViewHolder(parent, viewType)
        }
        return vh
    }

    abstract fun onNewCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int, payloads: List<*>) {
        if (holder is FooterViewHolder) {
            holder.bindDataAndListener(this, if (showNoDataFoot) if (type == ListFooterType.FOOT_LOADING_MORE)
                false else itemCount == 1 else true, mLMIF, type, footerText)
        } else {
            onNewBindViewHolder(holder, position, payloads)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

    }

    abstract fun onNewBindViewHolder(holder: RecyclerView.ViewHolder, position: Int, payloads: List<*>)

    override fun getItemViewType(position: Int): Int {
        val footerPosition = itemCount - 1
        if (position == footerPosition && isEnabledFoot) {
            return 9999
        }
        val item_type_result = getNewItemViewType(position)
        if (item_type_result == 9999) {
            throw RuntimeException("该itemType已经存在值为9999的情况，不能再返回9999")
        }
        return item_type_result
    }

    abstract fun getNewItemViewType(position: Int): Int

}
