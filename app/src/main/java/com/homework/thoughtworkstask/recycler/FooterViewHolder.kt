package com.homework.thoughtworkstask.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import com.homework.thoughtworkstask.R

class FooterViewHolder(parent: ViewGroup) : BaseViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recycler_footview, parent, false)) {

    private val layoutFooter: LinearLayout = getView(R.id.layout_footer)
    private val llNoMore:LinearLayout = getView(R.id.llNoMore)
    private val proFooter: ProgressBar = getView(R.id.pro_footer)
    private val textFooter: TextView = getView(R.id.text_footer)
    private val noProgress: TextView = getView(R.id.tvNoMoreData)


    fun bindDataAndListener(footViewAdapter: BaseRecyclerViewAdapter<*>, isFirst: Boolean, iFVA: (()->Unit)?, type: Int, footText: String) {
        isShow(isFirst)
        var isShow = false
        layoutFooter.setOnClickListener(null)
        if (type == ListFooterType.FOOT_LOADING_ADD || type == ListFooterType.FOOT_ERROR_LOADDATA) {
            layoutFooter.setOnClickListener {
                if (iFVA != null) {
                    footViewAdapter.setFootType(ListFooterType.FOOT_LOADING_MORE)
                    iFVA.invoke()
                }
            }
        }
        if (type == ListFooterType.FOOT_LOADING_MORE) {
            isShow = true
        }
        if(isShow){
            layoutFooter.visibility = View.VISIBLE
            llNoMore.visibility = View.GONE
            textFooter.text = footText
        }else{
            layoutFooter.visibility = View.GONE
            llNoMore.visibility = View.VISIBLE
            noProgress.text = if (footText.isEmpty()) mContext.getString(R.string.no_loading_data) else footText
        }
    }

    private fun isShow(isFirst: Boolean) {
        layoutFooter.visibility = if (isFirst) View.GONE else View.VISIBLE
    }
}
