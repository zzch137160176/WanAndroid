package per.zzch.library.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import per.zzch.library.utils.BasePage
import per.zzch.library.utils.LogUtil
import per.zzch.library.widget.FooterHolder

/**
 *
 * @author :曾佐丞
 * @e-mail :zengzuocheng@jimimax.com
 * @date   :2019/11/4
 * @desc   :
 */

abstract class BasePageRecyclerAdapter<P : BasePage<T>, T>(private val layout: Int) :
    BaseRecyclerAdapterK<T>(layout) {

    protected val typeHeaderView = Integer.MIN_VALUE
    protected val typeFooterView = Integer.MIN_VALUE + 1

    private var mHeadView: View? = null
    private var mFootView: View? = null

    fun addHeaderView(header: View) {
        mHeadView = header
        headViewCount = 1
        this.notifyDataSetChanged()
    }

    fun addFooterView(footer: View) {
        mFootView = footer
        footerViewCount = 1
        this.notifyDataSetChanged()
    }

    fun removeHeaderView() {
        mHeadView = null
        headViewCount = 0
        this.notifyDataSetChanged()
    }

    fun removeFooterView() {
        mFootView = null
        footerViewCount = 0
        this.notifyDataSetChanged()
    }

    private fun getHeaderViewsCount(): Int {
        return headViewCount
    }

    private fun getFooterViewsCount(): Int {
        return footerViewCount
    }

    private fun isHeader(position: Int): Boolean {
        return getHeaderViewsCount() > 0 && position == 0
    }

    fun isFooter(position: Int): Boolean {
        val lastPosition = getDataList().size + getHeaderViewsCount() + getFooterViewsCount() - 1
        return getFooterViewsCount() > 0 && position == lastPosition
    }

    override fun getItemCount(): Int {
        return getDataList().size + getHeaderViewsCount() + getFooterViewsCount()
    }

    override fun getItemViewType(position: Int): Int {
        val innerCount = getDataList().size
        val headViewCount = getHeaderViewsCount()
        return when {
            position < headViewCount -> typeHeaderView
            position < headViewCount + innerCount -> 0
            else -> typeFooterView
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerHolder {
        if (viewType == typeHeaderView) {
            return RecyclerHolder(mHeadView!!)
        } else if (viewType == typeFooterView) {
            return RecyclerHolder(mFootView!!)
        }
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return RecyclerHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerHolder, position: Int) {
        val headerViewsCountCount = getHeaderViewsCount()
        if (position >= headerViewsCountCount && position < headerViewsCountCount + getDataList().size) {
            super.onBindViewHolder(holder, position)
        }
    }

    private var onLoadListener: ((Int) -> Unit)? = null


    private var mFooter: FooterHolder? = null

    /**
     * 当前RecyclerView类型
     */
    protected var layoutManagerType: Int = 0
    private val gridLayout = 1
    private val linearLayout = 2
    private val staggeredGridLayout = 3
    /**
     * 最后一个的位置
     */
    private var lastPositions: IntArray? = null

    /**
     * 最后一个可见的item的位置
     */
    private var lastVisibleItemPosition: Int = 0

    private var startPage: Int = 0

    private var isLoading: Boolean = false

    private var noMore: Boolean = false

    private var pageNow: Int = 0

    fun setOnLoadListener(onLoadListener: (Int) -> Unit) {
        this.onLoadListener = onLoadListener
        init()
        hideFooter()
    }

    private fun init() {
        if (mFooter == null) {
            mFooter = FooterHolder(mRecyclerView)
            addFooterView(mFooter!!.view)
            val layoutManager = mRecyclerView.layoutManager
            // 对GridLayoutManager的特殊处理
            if (layoutManager != null && layoutManager is GridLayoutManager) {
                val spanCount = layoutManager.spanCount
                //相当于weight 值 spanCount 总weight
                layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                    override fun getSpanSize(position: Int): Int {
                        return if (isHeader(position) || isFooter(position))
                            spanCount
                        else
                            1
                    }
                }
            }
        }
        mRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (onLoadListener == null) {
                    return
                }
                val layoutManager = recyclerView.layoutManager ?: return

                if (layoutManagerType == 0) {
                    layoutManagerType = when (layoutManager) {
                        is GridLayoutManager -> gridLayout
                        is LinearLayoutManager -> linearLayout
                        is StaggeredGridLayoutManager -> staggeredGridLayout
                        else -> throw RuntimeException(
                            "Unsupported LayoutManager used. Valid ones are LinearLayoutManager, GridLayoutManager and StaggeredGridLayoutManager"
                        )
                    }
                }

                when (layoutManagerType) {
                    linearLayout -> lastVisibleItemPosition =
                        (layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                    gridLayout -> lastVisibleItemPosition =
                        (layoutManager as GridLayoutManager).findLastVisibleItemPosition()
                    staggeredGridLayout -> {
                        val staggeredGridLayoutManager =
                            layoutManager as StaggeredGridLayoutManager?
                        if (staggeredGridLayoutManager != null) {
                            if (lastPositions == null) {
                                lastPositions = IntArray(staggeredGridLayoutManager.spanCount)
                            }
                            staggeredGridLayoutManager.findLastVisibleItemPositions(lastPositions)
                            lastVisibleItemPosition = findMax(lastPositions!!)
                        }
                    }
                }

            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (onLoadListener == null)
                    return
                if (isLoading || noMore || pageNow == startPage)
                    return
                val layoutManager = recyclerView.layoutManager ?: return
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                if (visibleItemCount > 0
                    && newState == RecyclerView.SCROLL_STATE_IDLE
                    && lastVisibleItemPosition >= totalItemCount - 2
                ) {
                    isLoading = true
                    onLoadListener?.invoke(pageNow + 1)
                }
            }
        })

    }

    fun getPageNow(): Int {
        return pageNow
    }

    fun isNoMore(): Boolean {
        return noMore
    }

    fun getFooter(): FooterHolder {
        if (mFooter == null)
            init()
        return mFooter!!
    }

    fun setPageNow(page: P) {
        this.pageNow = page.page
        if (page.page == startPage) {
            setData(page.list)
        } else {
            addData(page.list)
        }

        mRecyclerView.postDelayed({
            if (pageNow >= page.totalPage) {
                noMore = true
                setFooterNoMore()
            } else {
                noMore = false
                resetFooter()
            }
            if (pageNow <= 0 && page.totalSize <= 5) {
                if (page.totalSize == 0) {
                    getFooter().showFootEmpty(mRecyclerView.height)
                } else {
                    hideFooter()
                }
            }
        }, 100)

        isLoading = false
    }

    private fun setFooterNoMore() {
        if (mFooter == null) {
            init()
        }
        mFooter?.setFooterNoMore()
    }

    fun hideFooter() {
        if (mFooter == null)
            return
        mFooter?.hideFooter()
    }

    fun getStartPage(): Int {
        return startPage
    }

    fun setStartPage(startPage: Int) {
        this.startPage = startPage
    }

    private fun resetFooter() {
        if (mFooter == null) {
            init()
        }
        mFooter?.reSetFooter()
    }

    /**
     * 取数组中最大值
     */
    private fun findMax(lastPositions: IntArray): Int {
        var max = lastPositions[0]
        for (value in lastPositions) {
            if (value > max) {
                max = value
            }
        }
        return max
    }
}