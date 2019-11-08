package per.zzch.wanandroid.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import per.zzch.library.retrofit.execute
import per.zzch.library.retrofit.sync
import per.zzch.wanandroid.model.Article
import per.zzch.wanandroid.model.Banner
import per.zzch.wanandroid.model.Page
import per.zzch.wanandroid.remote.getApi

/**
 *
 * @author :曾佐丞
 * @e-mail :zengzuocheng@jimimax.com
 * @date   :2019/11/6
 * @desc   :
 */
class BannerVM : ViewModel() {

    companion object {
        const val START_PAGE = 1
    }

    val mBannerList = MutableLiveData<List<Banner>>()

    val mPage = MutableLiveData<Page<Article>>()

    fun initData() {
        getBanner()
        getArticle(START_PAGE - 1)
    }

    private fun getBanner() {
        getApi().getBanner().sync().execute({
            it.data ?: return@execute
            mBannerList.postValue(it.data)
        }, {

        })
    }

    fun getArticle(page: Int) {
        getApi().getArticle(page).sync().execute({
            it.data ?: return@execute
            it.data.refreshPage()
            mPage.postValue(it.data)
        }, {

        })
    }
}