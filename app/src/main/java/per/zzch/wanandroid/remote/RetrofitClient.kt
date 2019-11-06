package per.zzch.wanandroid.remote

import per.zzch.library.utils.helper.RetrofitHelper

/**
 *
 * @author :曾佐丞
 * @e-mail :zengzuocheng@jimimax.com
 * @date   :2019/11/6
 * @desc   :
 */

private const val BASE_URL = "https://www.wanandroid.com/"

fun getApi(): API {
    return RetrofitHelper.createApi(API::class.java, BASE_URL)
}
