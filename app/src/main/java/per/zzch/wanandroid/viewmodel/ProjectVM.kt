package per.zzch.wanandroid.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import per.zzch.library.retrofit.execute
import per.zzch.library.retrofit.sync
import per.zzch.library.utils.LogUtil
import per.zzch.wanandroid.model.Article
import per.zzch.wanandroid.model.Page
import per.zzch.wanandroid.model.ProjectKind
import per.zzch.wanandroid.remote.getApi

/**
 *
 * @author :曾佐丞
 * @e-mail :zengzuocheng@jimimax.com
 * @date   :2019/11/6
 * @desc   :
 */
class ProjectVM : ViewModel() {

    companion object {
        const val DEFAULT_PROJECT_ID = 264
        const val START_PAGE = 1
    }

    val mKindList = MutableLiveData<List<ProjectKind>>()

    val mPage = MutableLiveData<Page<Article>>()

    fun init() {
        getKind()
        getProject(START_PAGE ,DEFAULT_PROJECT_ID)
    }

    fun getProject(page: Int, kindId: Int) {
        getApi().getProject(page, kindId).sync().execute({
            it.data ?: return@execute
            mPage.postValue(it.data)
        }, {

        })
    }

    private fun getKind() {
        getApi().getProjectKind().sync().execute({
            it.data ?: return@execute
            mKindList.postValue(it.data)
        }, {

        })
    }
}