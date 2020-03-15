package com.homework.thoughtworkstask.recycler
import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class LRecyclerView : RecyclerView {

    private var mContext: Context? = null
    private var mLayoutManager: LinearLayoutManager? = null
    private var mIgetOneInt: ((Int)->Unit)? = null //回调滑动过程
    private var mIgetOneInt2: ((Int)->Unit)? = null//回调滑动位置
    private var mScrollStyle: OnScrollStyle? = null       //回调滑动状态

    interface OnScrollStyle {
        fun getScrollStyle(arg0: Int)
    }

    fun setmIgetOneInt(mIgetOneInt: ((Int)->Unit)) {
        this.mIgetOneInt = mIgetOneInt
    }

    fun setmIgetOneInt2(mIgetOneInt: ((Int)->Unit)) {
        this.mIgetOneInt2 = mIgetOneInt
    }

    fun setmScrollStyle(mScrollStyle: OnScrollStyle) {
        this.mScrollStyle = mScrollStyle
    }

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle) {
        this.mContext = context
    }

    /**
     * 滑动到某个item的位置
     *
     * @param position
     */
    override fun scrollToPosition(position: Int) {
        if (mLayoutManager != null) {
            mLayoutManager!!.scrollToPositionWithOffset(position, 0)
        }
    }

    /**
     * 上下有加载更多
     *
     * @param mAdapter
     * @param moreInterface
     */
    fun init(mAdapter: BaseRecyclerViewAdapter<*>?, moreInterface: (()->Unit)?) {
        if (mAdapter == null) {
            return
        }
        mLayoutManager = object : LinearLayoutManager(mContext) {
            override fun onLayoutChildren(recycler: RecyclerView.Recycler?, state: RecyclerView.State) {
                try {
                    super.onLayoutChildren(recycler, state)
                } catch (e: IndexOutOfBoundsException) {
                }

            }
        }
        //        mLayoutManager.setAutoMeasureEnabled(true);
        layoutManager = mLayoutManager
        addOnScrollListener(object : RecyclerView.OnScrollListener() {
            private var lastVisibleItem: Int = 0

            override fun onScrollStateChanged(recyclerView: RecyclerView,
                                              newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == mAdapter.itemCount && mAdapter.itemCount > 1) {
                    if (moreInterface != null) {
                        mAdapter.setFootType(ListFooterType.FOOT_LOADING_MORE)
                        moreInterface.invoke()
                    }
                }
                if (mScrollStyle != null) {
                    mScrollStyle!!.getScrollStyle(newState)
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                lastVisibleItem = mLayoutManager!!.findLastVisibleItemPosition()
                if (mIgetOneInt2 != null) {
                    val firstItemPosition = mLayoutManager!!.findFirstVisibleItemPosition()
                    mIgetOneInt2!!.invoke(firstItemPosition)
                }
                if (mIgetOneInt != null) {
                    mIgetOneInt!!.invoke(dy)
                }
            }
        }
        )

        adapter = mAdapter
    }

    /**
     * 左右无加载更多
     *
     * @param mAdapter
     */
    fun initLeftRight(mAdapter: RecyclerView.Adapter<*>?) {
        if (mAdapter == null) {
            return
        }
        mLayoutManager = object : LinearLayoutManager(mContext) {
            //... constructor
            override fun onLayoutChildren(recycler: RecyclerView.Recycler?, state: RecyclerView.State) {
                try {
                    super.onLayoutChildren(recycler, state)
                } catch (e: IndexOutOfBoundsException) {
                }

            }
        }
        mLayoutManager!!.orientation = RecyclerView.HORIZONTAL
        layoutManager = mLayoutManager
        itemAnimator = DefaultItemAnimator()
        adapter = mAdapter
    }

    /**
     * 上下无加载更多
     *
     * @param mAdapter
     */
    fun init(mAdapter: RecyclerView.Adapter<*>?) {
        if (mAdapter == null) {
            return
        }
        addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView,
                                              newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (mScrollStyle != null) {
                    mScrollStyle!!.getScrollStyle(newState)
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (mIgetOneInt != null) {
                    mIgetOneInt!!.invoke(dy)
                }
            }
        })
        mLayoutManager = LinearLayoutManager(mContext)
        layoutManager = mLayoutManager
        itemAnimator = DefaultItemAnimator()
        adapter = mAdapter
    }
}
