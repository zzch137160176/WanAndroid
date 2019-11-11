package per.zzch.wanandroid.adapter

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_recycler_navigation.view.*
import per.zzch.library.adapter.BaseRecyclerAdapterJ
import per.zzch.library.adapter.StringRecyclerAdapter
import per.zzch.wanandroid.R
import per.zzch.wanandroid.model.Article
import per.zzch.wanandroid.model.Navigation

/**
 *
 * @author :曾佐丞
 * @e-mail :zengzuocheng@jimimax.com
 * @date   :2019/11/8
 * @desc   :
 */
class NavigationItemAdapter : BaseRecyclerAdapterJ<Navigation<Article>>() {
    override fun getLayoutResouse(): Int {
        return R.layout.item_recycler_navigation
    }

    override fun bindView(holder: ViewHolder, position: Int) {
        with(holder.itemView) {
            mTitle.text = dataList[position].name
            val adapter = StringRecyclerAdapter()
            val contentList = arrayListOf<String>()
            for (content: Article in dataList[position].articles ?: return@with) {
                contentList.add(content.title ?: continue)
            }
            adapter.dataList = contentList
            adapter.bindRecyclerView(mNavContentRv,GridLayoutManager(
                mNavContentRv.context, 3, RecyclerView.VERTICAL, false
            ))
        }

    }

}