package per.zzch.wanandroid.remote

import io.reactivex.Single
import per.zzch.wanandroid.model.Banner
import per.zzch.wanandroid.model.Result
import retrofit2.http.GET

/**
 *
 * @author :曾佐丞
 * @e-mail :zengzuocheng@jimimax.com
 * @date   :2019/11/6
 * @desc   :
 */
interface API {

    @GET("banner/json")
    fun getBanner(): Single<Result<List<Banner>>>

}