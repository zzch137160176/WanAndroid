package per.zzch.wanandroid.adapter

import android.annotation.SuppressLint
import android.view.View
import kotlinx.android.synthetic.main.item_recycler_article.view.*
import per.zzch.library.adapter.BasePageRecyclerAdapter
import per.zzch.library.utils.DateUtil
import per.zzch.wanandroid.R
import per.zzch.wanandroid.model.Article
import per.zzch.wanandroid.model.Page

/**
 *
 * @author :曾佐丞
 * @e-mail :zengzuocheng@jimimax.com
 * @date   :2019/11/7
 * @desc   :
 */

class ArticleItemAdapter :
    BasePageRecyclerAdapter<Page<Article>, Article>(R.layout.item_recycler_article) {

    @SuppressLint("SetTextI18n")
    override fun bind(holder: RecyclerHolder, item: Article) {
        with(holder.itemView) {
            mTime.text = DateUtil.getDate(item.publishTime)
            mTitle.text = item.title
            mKind.text = "分类：${item.superChapterName}"

            if (item.author.isNullOrEmpty() ) {
                mShared.visibility = View.VISIBLE
                mAuthor.visibility = View.GONE
                mShared.text = "分享者：${item.shareUser}"
            } else {
                mAuthor.visibility = View.VISIBLE
                mShared.visibility = View.GONE
                mAuthor.text = "作者：${item.author}"

            }
        }
    }

}