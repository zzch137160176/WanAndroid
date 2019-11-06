package per.zzch.library.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
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

    private var mDataList: List<T>? = null

    private var mListener: ((Int, T) -> Unit)? = null

    abstract fun bindView(holder: ViewHolder<B>, item: T)

    fun setDataList(datas: List<T>?) {
        mDataList = datas
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(doing: (Int, T) -> Unit) {
        mListener = doing
    }

    override fun onBindViewHolder(holder: ViewHolder<B>, position: Int) {
        if (!mDataList.isNullOrEmpty()) {
            bindView(holder, mDataList!![position])
            holder.binding.root.setOnClickListener {
                if (mListener != null) {
                    mListener?.invoke(holder.layoutPosition, mDataList!![position])
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
        return mDataList?.size ?: 0
    }

    inner class ViewHolder<B : ViewDataBinding>(val binding: B) :
        RecyclerView.ViewHolder(binding.root)

}