package com.homework.thoughtworkstask.recycler

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView


class GRecyclerView @JvmOverloads constructor(private val mContext: Context, attrs: AttributeSet? = null, defStyle: Int = 0) : RecyclerView(mContext, attrs, defStyle) {


    private var mGridLayoutManager: GridLayoutManager? = null
    var columns = 0

    private var mIgetOneInt: ((Int)->Unit)? = null

    /**
     * 滑动到某个item的位置
     *
     * @param position
     */
    override fun scrollToPosition(position: Int) {
        if (mGridLayoutManager != null) {
            mGridLayoutManager!!.scrollToPositionWithOffset(position, 0)
        }
    }

    /**
     * 有加载更多
     *
     * @param mAdapter
     * @param iFVA
     */
    fun init(mAdapter: RecyclerViewAdapter<*>?, iFVA:(()->Unit)) {
        if (mAdapter == null) {
            return
        }
        if (columns != 0) {
            mGridLayoutManager = GridLayoutManager(mContext, columns)
        } else {
            mGridLayoutManager = GridLayoutManager(mContext, 2)
        }
        layoutManager = mGridLayoutManager
        itemAnimator = DefaultItemAnimator()
        addOnScrollListener(object : RecyclerView.OnScrollListener() {
            private var lastVisibleItem: Int = 0

            override fun onScrollStateChanged(recyclerView: RecyclerView,
                                              newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItem + 1 == mAdapter.itemCount && mAdapter.itemCount > 1) {
                    mAdapter.setFootType(ListFooterType.FOOT_LOADING_MORE)
                    iFVA.invoke()
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                lastVisibleItem = mGridLayoutManager!!.findLastVisibleItemPosition()
                if (mIgetOneInt != null) {
                    mIgetOneInt!!.invoke(dy)
                }
            }
        })
        adapter = mAdapter
    }

    fun init(mAdapter: BaseViewAdapter<*>?) {
        if (mAdapter == null) {
            return
        }
        if (columns != 0) {
            mGridLayoutManager = GridLayoutManager(mContext, columns)
        } else {
            mGridLayoutManager = GridLayoutManager(mContext, 2)
        }
        layoutManager = mGridLayoutManager
        itemAnimator = DefaultItemAnimator()
        addOnScrollListener(object : RecyclerView.OnScrollListener() {
            private var lastVisibleItem: Int = 0

            override fun onScrollStateChanged(recyclerView: RecyclerView,
                                              newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItem + 1 == mAdapter.itemCount && mAdapter.itemCount > 1) {
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                lastVisibleItem = mGridLayoutManager!!.findLastVisibleItemPosition()
                if (mIgetOneInt != null) {
                    mIgetOneInt!!.invoke(dy)
                }
            }
        })
        adapter = mAdapter
    }

    fun setmIgetOneInt(mIgetOneInt: ((Int)->Unit)?) {
        this.mIgetOneInt = mIgetOneInt
    }

}
