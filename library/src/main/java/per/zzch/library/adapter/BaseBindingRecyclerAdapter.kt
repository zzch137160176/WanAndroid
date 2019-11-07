package per.zzch.library.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 *
 * @author :曾佐丞
 * @e-mail :zengzuocheng@jimimax.com
 * @date   :2019/11/4
 * @desc   :
 */

abstract class BaseBindingRecyclerAdapter<B : ViewDataBinding, T>(private val layout: Int) :
    RecyclerView.Adapter<BaseBindingRecyclerAdapter<B, T>.ViewHolder<B>>() {

    protected var headViewCount: Int = 0
    protected var footerViewCount: Int = 0

    private var mDataList = ArrayList<T>()

    private var mListener: ((Int, T) -> Unit)? = null

    abstract fun bindView(holder: ViewHolder<B>, item: T)

    fun getDataList(): List<T> {
        return mDataList
    }

    fun setDataList(dataList: List<T>?) {
        if (dataList == null) {
            mDataList.clear()
        } else {
            mDataList = dataList as ArrayList<T>
        }
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(doing: (Int, T) -> Unit) {
        mListener = doing
    }

    fun init(recyclerView: RecyclerView, layoutManager: RecyclerView.LayoutManager) {
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = this
    }

    override fun onBindViewHolder(holder: ViewHolder<B>, position: Int) {
        if (mDataList.isNotEmpty()) {
            bindView(holder, mDataList[position])
            holder.binding.root.setOnClickListener {
                if (mListener != null) {
                    mListener?.invoke(holder.layoutPosition, mDataList[position])
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<B> {
        val binding =
            DataBindingUtil.inflate<B>(LayoutInflater.from(parent.context), layout, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return mDataList.size
    }

    inner class ViewHolder<B : ViewDataBinding>(val binding: B) :
        RecyclerView.ViewHolder(binding.root)

}