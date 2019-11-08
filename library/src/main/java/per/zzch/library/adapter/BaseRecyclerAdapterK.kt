package per.zzch.library.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerAdapterK<ITEM>(private val layout: Int) :
    RecyclerView.Adapter<BaseRecyclerAdapterK<ITEM>.RecyclerHolder>() {

    private var dataList = ArrayList<ITEM>()

    protected var headViewCount: Int = 0
    protected var footerViewCount: Int = 0

    private var itemClickDoing: ((Int, ITEM) -> Unit)? = null
    private var itemLongClickDoing: ((Int, ITEM) -> Unit)? = null

    fun setOnItemClickListener(doing: (Int, ITEM) -> Unit) {
        itemClickDoing = doing
    }

    fun setOnItemLongClickListener(doing: (Int, ITEM) -> Unit) {
        itemLongClickDoing = doing
    }

    fun setData(dataList: List<ITEM>?) {
        if (dataList == null) {
            clearData()
            return
        }
        this.dataList = dataList as ArrayList<ITEM>
        notifyDataSetChanged()
    }

    fun removeItem(position: Int) {
        dataList.removeAt(position)
        notifyItemRemoved(position)
    }

    fun getItem(position: Int): ITEM {
        return dataList[position]
    }

    fun insertItem(item: ITEM, position: Int) {
        var notifyItem = position
        when {
            notifyItem >= itemCount -> {
                notifyItem = itemCount
                dataList.add(item)
            }
            notifyItem < 0 -> {
                notifyItem = 0
                dataList.add(notifyItem, item)
            }
            else -> dataList.add(notifyItem, item)
        }
        notifyItemInserted(notifyItem)
        //        notifyDataSetChanged();
    }

    open fun clearData() {
        dataList.clear()
        notifyDataSetChanged()
    }


    fun addData(dataList: List<ITEM>?) {
        if (dataList == null)
            return
        val old = this.dataList.size
        val count = dataList.size
        this.dataList.addAll(dataList)
        notifyItemRangeInserted(old, count)
    }

    fun addNewData(item: ITEM) {
        this.dataList.add(item)
        notifyItemInserted(itemCount - 1)
//        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerHolder {
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return RecyclerHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerHolder, position: Int) {
        if (itemClickDoing != null) {
            holder.itemView.setOnClickListener {
                val positionReal = getPosition(holder)
                itemClickDoing?.invoke(positionReal, dataList[positionReal])
            }
        }
        if (itemLongClickDoing != null) {
            holder.itemView.setOnLongClickListener {
                val positionReal = getPosition(holder)
                itemLongClickDoing?.invoke(positionReal, dataList[positionReal])
                true
            }
        }
        bind(holder, getItem(position))
    }

    override fun getItemCount(): Int = dataList.size

    fun getDataList(): ArrayList<ITEM> = dataList

    fun getDataSize(): Int = getDataList().size

    protected abstract fun bind(holder: RecyclerHolder, item: ITEM)

    protected lateinit var mRecyclerView: RecyclerView

    fun bindRecyclerView(
        recyclerView: RecyclerView,
        layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(
            recyclerView.context, RecyclerView.VERTICAL, false
        )
    ) {
        recyclerView.layoutManager = layoutManager
        mRecyclerView = recyclerView
        recyclerView.adapter = this
    }

    /**获取当前真实position*/
    fun getPosition(holder: RecyclerHolder): Int {
        return holder.layoutPosition - headViewCount
    }

    inner class RecyclerHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
