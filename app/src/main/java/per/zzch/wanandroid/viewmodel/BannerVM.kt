package per.zzch.wanandroid.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import per.zzch.library.retrofit.execute
import per.zzch.library.retrofit.sync
import per.zzch.library.utils.ToastUtil
import per.zzch.wanandroid.model.Banner
import per.zzch.wanandroid.remote.getApi

/**
 *
 * @author :曾佐丞
 * @e-mail :zengzuocheng@jimimax.com
 * @date   :2019/11/6
 * @desc   :
 */
class BannerVM :ViewModel() {

    val mBannerList = MutableLiveData<List<Banner>>()

    fun initData() {
        getApi().getBanner().sync().execute({
            if (it.data == null){
                ToastUtil.show("获取Banner信息失败")
                return@execute
            } else{
                mBannerList.postValue(it.data)
            }
        },{
            ToastUtil.show("获取Banner信息失败")
        })
    }

}