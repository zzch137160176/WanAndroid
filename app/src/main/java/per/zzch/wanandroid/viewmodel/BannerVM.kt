package per.zzch.wanandroid.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import per.zzch.library.retrofit.execute
import per.zzch.library.retrofit.sync
import per.zzch.library.utils.LogUtil
import per.zzch.library.utils.ToastUtil
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

    val mBannerList = MutableLiveData<List<Banner>>()

    val mPage = MutableLiveData<Page<Article>>()

    fun initData() {
        getBanner()
    }

    private fun getBanner() {
        getApi().getBanner().sync().execute({
            if (it.data == null) {
                ToastUtil.show("获取Banner信息失败")
                return@execute
            } else {
                mBannerList.postValue(it.data)
            }
        }, {
            ToastUtil.show("获取Banner信息失败")
        })
    }

    fun getArticle(page: Int) {
        getApi().getArticle(page).sync().execute({
            if (it.data == null) {
                ToastUtil.show("获取首页文章信息失败")
                return@execute
            } else {
                it.data.refreshPage()
                mPage.postValue(it.data)
            }
        }, {
            LogUtil.e(it.message)
//            ToastUtil.show("获取首页文章信息失败")
        })
    }
}