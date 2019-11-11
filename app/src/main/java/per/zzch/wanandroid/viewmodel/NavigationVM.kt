package per.zzch.wanandroid.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import per.zzch.library.retrofit.execute
import per.zzch.library.retrofit.sync
import per.zzch.wanandroid.model.Article
import per.zzch.wanandroid.model.Navigation
import per.zzch.wanandroid.remote.getApi

/**
 *
 * @author :曾佐丞
 * @e-mail :zengzuocheng@jimimax.com
 * @date   :2019/11/6
 * @desc   :
 */
class NavigationVM : ViewModel() {

    val mNavigationList = MutableLiveData<List<Navigation<Article>>>()

    fun init() {
        getNav()
    }

    private fun getNav() {
        getApi().getNavigation().sync().execute({
            it.data ?: return@execute
            mNavigationList.postValue(it.data)
        }, {

        })
    }

}