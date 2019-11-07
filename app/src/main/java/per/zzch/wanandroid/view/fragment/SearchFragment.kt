package per.zzch.wanandroid.view.fragment

import per.zzch.library.base.BaseBindingFragment
import per.zzch.wanandroid.R
import per.zzch.wanandroid.databinding.ArticleB
import per.zzch.wanandroid.viewmodel.ArticleVM

/**
 *
 * @author :曾佐丞
 * @e-mail :zengzuocheng@jimimax.com
 * @date   :2019/11/6
 * @desc   :
 */
class SearchFragment : BaseBindingFragment<ArticleVM, ArticleB>(R.layout.fragment_search) {

    companion object {
        fun newInstance() = SearchFragment()
    }

    override fun initEventAndData() {

    }

    override fun onActivityCreate() {

    }

}